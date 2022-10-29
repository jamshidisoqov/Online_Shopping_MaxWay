package uz.gita.online_shopping.data.models

import java.sql.Timestamp

// Created by Jamshid Isoqov an 10/9/2022
abstract class BaseModel(
    var id: Long,
    var createdDate: Timestamp
)