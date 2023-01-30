package com.example.followersofthecups

import android.os.Parcel
import android.os.Parcelable

data class User (
    val userName: String? = null,
    val password: String? = null,
    val email: String? = null,
    val userId: Int? = null,
    val supreme: Boolean?,
    val supremeRank: Int?,
    val supremeStreak: Int?
    ) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Boolean::class.java.classLoader) as? Boolean,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(userName)
        parcel.writeString(password)
        parcel.writeString(email)
        parcel.writeValue(userId)
        parcel.writeValue(supreme)
        parcel.writeValue(supremeRank)
        parcel.writeValue(supremeStreak)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }
    }
}



