package com.vaayugo.core.domain.model

// ── Session (stored in DataStore after login) ──────────────────────────────
data class Session(
    val token  : String,
    val role   : String,   // "customer" | "shopkeeper" | "admin"
    val userId : String,
    val name   : String,
    val email  : String
)

// ── User ──────────────────────────────────────────────────────────────────
data class User(
    val id      : String,
    val name    : String,
    val email   : String,
    val phone   : String,
    val role    : String,
    val address : String,
    val location: String,
    val status  : String,   // "active" | "blocked"
    val avatar  : String?
)

// ── Shop ──────────────────────────────────────────────────────────────────
data class Shop(
    val id            : String,
    val name          : String,
    val location      : String,
    val address       : String,
    val category      : String,
    val status        : String,   // "approved" | "pending" | "blocked"
    val isOpen        : Boolean,
    val shopRating    : Float,
    val deliveryRating: Float,
    val images        : List<String>,
    val ownerId       : String
)

// ── Product ───────────────────────────────────────────────────────────────
data class Product(
    val id       : String,
    val name     : String,
    val price    : Double,
    val stock    : Int,
    val imageUrl : String?,
    val status   : String,   // "in_stock" | "out_of_stock"
    val shopId   : String,
    val category : String
)

// ── Cart (local state — not persisted to server until checkout) ────────────
data class Cart(
    val shopId      : String,
    val shopName    : String,
    val items       : List<CartItem>,
    val deliveryFee : Double,
    val discount    : Double
) {
    val subtotal: Double get() = items.sumOf { item: CartItem -> item.price * item.quantity }
    val total   : Double get() = subtotal + deliveryFee - discount
}

data class CartItem(
    val productId : String,
    val name      : String,
    val price     : Double,
    val quantity  : Int,
    val imageUrl  : String?
)

// ── Order ─────────────────────────────────────────────────────────────────
data class Order(
    val id                 : String,
    val shopId             : String,
    val shopName           : String,
    val customerId         : String,
    val customerName       : String,
    val customerPhone      : String,
    val customerEmail      : String,
    val deliveryAddress    : String,
    val items              : List<OrderItem>,
    val subtotal           : Double,
    val deliveryFee        : Double,
    val discount           : Double,
    val total              : Double,
    val status             : OrderStatus,
    val cancellationReason : String?,
    val failureReason      : String?,
    val shopRating         : Float?,
    val deliveryRating     : Float?,
    val createdAt          : String,
    val deliverySlot       : String?
)

data class OrderItem(
    val productId : String,
    val name      : String,
    val price     : Double,
    val quantity  : Int
)

enum class OrderStatus {
    PENDING, PROCESSING, DELIVERED, CANCELLED, FAILED
}

// ── Settlement (shopkeeper earnings breakdown) ─────────────────────────────
data class Settlement(
    val totalOrders     : Int,
    val totalOrderValue : Double,
    val commission      : Double,
    val deliveryShare   : Double,
    val netEarnings     : Double
)

// ── Service Rule (admin delivery fee config) ───────────────────────────────
data class ServiceRule(
    val id                : String,
    val scopeLocation     : String,
    val scopeShop         : String?,
    val scopeCategory     : String?,
    val totalDeliveryFee  : Double,
    val shopDeliveryShare : Double,
    val vaayugoShare      : Double,
    val commissionPercent : Double,
    val minOrderValue     : Double,
    val smallOrderFee     : Double?,
    val isActive          : Boolean
)

// ── Discount ──────────────────────────────────────────────────────────────
data class Discount(
    val id        : String,
    val name      : String,
    val type      : String,   // "percentage" | "flat"
    val value     : Double,
    val scope     : String,   // "global" | "shop" | "product"
    val maxCap    : Double?,
    val minOrder  : Double?,
    val isActive  : Boolean,
    val createdBy : String
)

// ── Delivery Slot ─────────────────────────────────────────────────────────
data class DeliverySlot(
    val id         : String,
    val name       : String,
    val startTime  : String,
    val endTime    : String,
    val cutoffTime : String,
    val isActive   : Boolean
)

// ── Admin Dashboard Stats ─────────────────────────────────────────────────
data class AdminStats(
    val avgOrderValue      : Double,
    val avgPlatformRevenue : Double,
    val revenueByLocation  : List<RevenueEntry>,
    val revenueByCategory  : List<RevenueEntry>,
    val topGrossingShops   : List<RevenueEntry>,
    val recentDailyRevenue : List<DailyRevenue>
)

data class RevenueEntry(val label: String, val amount: Double)
data class DailyRevenue(val date: String, val amount: Double)