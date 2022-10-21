package uz.gita.online_shopping.data.models.enums

// Created by Jamshid Isoqov an 10/8/2022
enum class OrderStatus {
    ORDERED, CONFIRMED, ON_THE_WAY, DELIVERED;

    fun getOnTheSelf(count:Int) = if(count==3) listOf(ORDERED.name,CONFIRMED.name,DELIVERED.name) else
        listOf(ORDERED.name,CONFIRMED.name,ON_THE_WAY.name,DELIVERED.name)
}
