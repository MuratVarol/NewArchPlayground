package com.example.newarchplayground.data

import com.google.gson.internal.LinkedTreeMap

data class SearchResult(val data:LinkedTreeMap<String,Any>)

data class PropertyData(val imgUrl:String,val name:String)
