import os
import urllib.request
import zlib

BASE_DIR = os.path.dirname(os.path.dirname(__file__))
DIAGRAMS_DIR = os.path.join(BASE_DIR, 'diagrams')
OUT_DIR = os.path.join(BASE_DIR, 'screenshots')
SERVER = 'http://www.plantuml.com/plantuml/png/'

os.makedirs(OUT_DIR, exist_ok=True)

def encode_plantuml(text: bytes) -> str:
    compressed = zlib.compressobj(level=9, wbits=-15)
    data = compressed.compress(text) + compressed.flush()
    # custom base64 for PlantUML
    alphabet = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz-_"
    def append3bytes(b1, b2, b3):
        c1 = b1 >> 2
        c2 = ((b1 & 0x3) << 4) | (b2 >> 4)
        c3 = ((b2 & 0xF) << 2) | (b3 >> 6)
        c4 = b3 & 0x3F
        return alphabet[c1] + alphabet[c2] + alphabet[c3] + alphabet[c4]

    res = []
    i = 0
    length = len(data)
    while i < length:
        b1 = data[i]
        b2 = data[i+1] if i+1 < length else 0
        b3 = data[i+2] if i+2 < length else 0
        res.append(append3bytes(b1, b2, b3))
        i += 3
    return ''.join(res)

for fname in os.listdir(DIAGRAMS_DIR):
    if not fname.endswith('.puml'):
        continue
    path = os.path.join(DIAGRAMS_DIR, fname)
    with open(path, 'rb') as f:
        data = f.read()
    try:
        enc = encode_plantuml(data)
        url = SERVER + enc
        # write link into diagram_links.md
        links_md = os.path.join(DIAGRAMS_DIR, 'diagram_links.md')
        with open(links_md, 'a', encoding='utf-8') as lm:
            lm.write(f'- {fname}: {url}\n')

        # Try multiple servers and methods
        tried = []
        success = False
        servers = [
            ('https://www.plantuml.com/plantuml/png/', 'GET_ENCODED'),
            ('https://www.plantuml-server.com/plantuml/png/', 'GET_ENCODED'),
        ]
        headers = {'User-Agent': 'Mozilla/5.0', 'Accept': 'image/png'}
        for base, mode in servers:
            try:
                full = base + enc
                req = urllib.request.Request(full, headers=headers)
                with urllib.request.urlopen(req, timeout=30) as resp:
                    out = resp.read()
                    out_path = os.path.join(OUT_DIR, fname.replace('.puml', '.png'))
                    with open(out_path, 'wb') as of:
                        of.write(out)
                    print('WROTE:'+out_path)
                    success = True
                    break
            except Exception as e:
                tried.append((base, str(e)))

        # Fallback: use Kroki POST (send raw PlantUML text)
        if not success:
            try:
                kroki_url = 'https://kroki.io/plantuml/png'
                req = urllib.request.Request(kroki_url, data=data, headers={'User-Agent':'Mozilla/5.0','Content-Type':'text/plain'})
                with urllib.request.urlopen(req, timeout=30) as resp:
                    out = resp.read()
                    out_path = os.path.join(OUT_DIR, fname.replace('.puml', '.png'))
                    with open(out_path, 'wb') as of:
                        of.write(out)
                    print('WROTE_KROKI:'+out_path)
                    success = True
            except Exception as e:
                tried.append(('kroki', str(e)))

        if not success:
            print('FAILED_ALL_TRIES for '+fname)
            for t in tried:
                print('TRIED:', t[0], t[1])
    except Exception as e:
        print('ERROR:'+fname+':'+str(e))
