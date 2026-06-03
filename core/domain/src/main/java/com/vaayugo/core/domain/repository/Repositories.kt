package com.vaayugo.core.domain.repository

import com.vaayugo.core.domain.model.AdminStats
import com.vaayugo.core.domain.model.Cart
import com.vaayugo.core.domain.model.DeliverySlot
import com.vaayugo.core.domain.model.Discount
import com.vaayugo.core.domain.model.Order
import com.vaayugo.core.domain.model.OrderStatus
import com.vaayugo.core.domain.model.Product
import com.vaayugo.core.domain.model.ServiceRule
import com.vaayugo.core.domain.model.Session
import com.vaayugo.core.domain.model.Settlement
import com.vaayugo.core.domain.model.Shop
import com.vaayugo.core.domain.model.User
import kotlinx.coroutines.flow.Flow

// ── Auth ──────────────────────────────────────────────────────────────────
interface AuthRepository {
    suspend fun login(email: String, password: String): Result<Session>
    suspend fun register(
        name     : String,
        email    : String,
        phone    : String,
        password : String,
        role     : String
    ): Result<Session>
    fun getSessionFlow(): Flow<Session?>
    suspend fun clearSession()
}

// ── Products ──────────────────────────────────────────────────────────────
interface ProductRepository {
    suspend fun getShopProducts(shopId: String): Result<List<Product>>
    suspend fun createProduct(
        shopId    : String,
        name      : String,
        price     : Double,
        stock     : Int,
        category  : String,
        imageBytes: ByteArray?
    ): Result<Product>
    suspend fun updateProduct(
        productId : String,
        name      : String,
        price     : Double,
        stock     : Int
    ): Result<Product>
    suspend fun deleteProduct(productId: String): Result<Unit>
    suspend fun bulkUpload(
        shopId   : String,
        csvBytes : ByteArray,
        zipBytes : ByteArray?
    ): Result<Unit>
}

// ── Orders ────────────────────────────────────────────────────────────────
interface OrderRepository {
    suspend fun placeOrder(
        cart    : Cart,
        address : String,
        slotId  : String?
    ): Result<Order>
    suspend fun getMyOrders(): Result<List<Order>>
    suspend fun getShopOrders(shopId: String): Result<List<Order>>
    suspend fun updateOrderStatus(
        orderId : String,
        status  : OrderStatus,
        reason  : String?
    ): Result<Order>
    suspend fun getAllOrders(): Result<List<Order>>
}

// ── Shops ─────────────────────────────────────────────────────────────────
interface ShopRepository {
    suspend fun getAllShops(): Result<List<Shop>>
    suspend fun getShopById(shopId: String): Result<Shop>
    suspend fun updateShop(
        shopId   : String,
        name     : String,
        address  : String,
        category : String
    ): Result<Shop>
    suspend fun updateShopImages(
        shopId     : String,
        imageBytes : List<ByteArray>
    ): Result<Shop>
    suspend fun toggleShopOpen(shopId: String, isOpen: Boolean): Result<Shop>
    suspend fun getMyShop(): Result<Shop>
    suspend fun getSettlement(shopId: String): Result<Settlement>
}

// ── Admin ─────────────────────────────────────────────────────────────────
interface AdminRepository {
    suspend fun getDashboardStats(): Result<AdminStats>
    suspend fun getAllUsers(): Result<List<User>>
    suspend fun updateUserStatus(userId: String, status: String): Result<User>
}

// ── Rules ─────────────────────────────────────────────────────────────────
interface RulesRepository {
    suspend fun getServiceRules(): Result<List<ServiceRule>>
    suspend fun createServiceRule(rule: ServiceRule): Result<ServiceRule>
    suspend fun deleteServiceRule(ruleId: String): Result<Unit>
    suspend fun getDiscounts(): Result<List<Discount>>
    suspend fun createDiscount(discount: Discount): Result<Discount>
    suspend fun toggleDiscount(discountId: String, isActive: Boolean): Result<Discount>
    suspend fun deleteDiscount(discountId: String): Result<Unit>
}

// ── Delivery Slots ────────────────────────────────────────────────────────
interface SlotRepository {
    suspend fun getSlots(): Result<List<DeliverySlot>>
    suspend fun createSlot(
        name       : String,
        startTime  : String,
        endTime    : String,
        cutoffTime : String
    ): Result<DeliverySlot>
    suspend fun deleteSlot(slotId: String): Result<Unit>
}

// ── Cart (local — no network) ─────────────────────────────────────────────
interface CartRepository {
    fun getCartFlow(): Flow<Cart?>
    suspend fun addItem(
        product  : Product,
        shopId   : String,
        shopName : String
    ): Result<Unit>
    suspend fun updateQuantity(productId: String, quantity: Int): Result<Unit>
    suspend fun clearCart(): Result<Unit>
    suspend fun setDeliveryFee(fee: Double)
}