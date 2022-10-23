package uz.gita.online_shopping.data.prefs

import android.content.Context
import android.content.SharedPreferences
import uz.gita.online_shopping.utils.SharedPreference
import javax.inject.Inject

// Created by Jamshid Isoqov an 9/23/2022
class MySharedPref @Inject constructor(
    ctx: Context,
    sharedPreferences: SharedPreferences
) : SharedPreference(ctx, sharedPreferences) {

    var token: String by Strings("")

    var phone: String by Strings()

    var name: String by Strings()

    var birthday: String by Strings()
}