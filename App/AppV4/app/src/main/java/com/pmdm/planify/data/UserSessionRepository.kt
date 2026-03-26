package com.pmdm.planify.data

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class UserSessionRepository @Inject constructor(
    @ApplicationContext context: Context
) {
    private val prefs = context.getSharedPreferences("planify_session", Context.MODE_PRIVATE)

    fun setCurrentUserEmail(email: String) {
        prefs.edit().putString(KEY_CURRENT_USER_EMAIL, email.trim()).apply()
    }

    fun getCurrentUserEmail(): String? =
        prefs.getString(KEY_CURRENT_USER_EMAIL, null)?.takeIf { it.isNotBlank() }

    fun clearSession() {
        prefs.edit().remove(KEY_CURRENT_USER_EMAIL).apply()
    }

    companion object {
        private const val KEY_CURRENT_USER_EMAIL = "current_user_email"
    }
}
