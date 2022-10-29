package uz.gita.online_shopping.data.models

import uz.gita.online_shopping.data.models.dto.ProductDto


class ProductItem(
    val product: ProductDto? = null,
    var quantity: Int = 0
)
