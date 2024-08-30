package com.example.jobapplokal.data_layer.room.converter

import androidx.room.TypeConverter
import com.example.jobapplokal.data_layer.model.*
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.google.gson.reflect.TypeToken

class Converter {

    private val gson = Gson()

    @TypeConverter
    fun fromContactPreference(contactPreference: ContactPreference?): String? {
        return contactPreference?.let { gson.toJson(it) }
    }

    @TypeConverter
    fun toContactPreference(contactPreferenceString: String?): ContactPreference? {
        return contactPreferenceString?.let { gson.fromJson(it, ContactPreference::class.java) }
    }

    @TypeConverter
    fun fromContentV3(contentV3: ContentV3?): String? {
        return contentV3?.let { gson.toJson(it) }
    }

    @TypeConverter
    fun toContentV3(contentV3String: String?): ContentV3? {
        return contentV3String?.let { gson.fromJson(it, ContentV3::class.java) }
    }

    @TypeConverter
    fun fromCreativeList(creatives: List<Creative>?): String? {
        return creatives?.let { gson.toJson(it) }
    }

    @TypeConverter
    fun toCreativeList(creativeString: String?): List<Creative>? {
        return creativeString?.let {
            val listType = object : TypeToken<List<Creative>>() {}.type
            gson.fromJson(it, listType)
        }
    }

    @TypeConverter
    fun fromFeeDetails(feeDetails: FeeDetails?): String? {
        return feeDetails?.let { gson.toJson(it) }
    }

    @TypeConverter
    fun toFeeDetails(feeDetailsString: String?): FeeDetails? {
        return feeDetailsString?.let { gson.fromJson(it, FeeDetails::class.java) }
    }

    @TypeConverter
    fun fromJobTagList(jobTags: List<JobTag>?): String? {
        return jobTags?.let { gson.toJson(it) }
    }

    @TypeConverter
    fun toJobTagList(jobTagString: String?): List<JobTag>? {
        return jobTagString?.let {
            val listType = object : TypeToken<List<JobTag>>() {}.type
            gson.fromJson(it, listType)
        }
    }

    @TypeConverter
    fun fromLocationList(locations: List<Location>?): String? {
        return locations?.let { gson.toJson(it) }
    }

    @TypeConverter
    fun toLocationList(locationString: String?): List<Location>? {
        return locationString?.let {
            val listType = object : TypeToken<List<Location>>() {}.type
            gson.fromJson(it, listType)
        }
    }

    @TypeConverter
    fun fromPrimaryDetails(primaryDetails: PrimaryDetails?): String? {
        return primaryDetails?.let { gson.toJson(it) }
    }

    @TypeConverter
    fun toPrimaryDetails(primaryDetailsString: String?): PrimaryDetails? {
        return primaryDetailsString?.let { gson.fromJson(it, PrimaryDetails::class.java) }
    }

    @TypeConverter
    fun fromTranslatedContent(translatedContent: TranslatedContent?): String? {
        return translatedContent?.let { gson.toJson(it) }
    }

    @TypeConverter
    fun toTranslatedContent(translatedContentString: String?): TranslatedContent? {
        return translatedContentString?.let { gson.fromJson(it, TranslatedContent::class.java) }
    }

    @TypeConverter
    fun fromListObject(value: List<Any>?): String? {
        return value?.let { gson.toJson(it) }
    }

    @TypeConverter
    fun toListObject(value: String?): List<Any>? {
        return value?.let {
            val listType = object : TypeToken<List<Any>>() {}.type
            gson.fromJson(it, listType)
        }
    }
}