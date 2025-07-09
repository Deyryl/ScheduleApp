package com.vnmhpractice.scheduleapp.android.ui.main.menu.account

import com.vnmhpractice.scheduleapp.android.datasource.DataSource
import com.vnmhpractice.scheduleapp.models.User

data class AccountUiState(
    val user: User = DataSource.users.first()
)
