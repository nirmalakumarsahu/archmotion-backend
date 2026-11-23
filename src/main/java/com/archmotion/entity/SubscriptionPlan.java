package com.archmotion.entity;

import com.archmotion.auditing.Auditable;
import com.archmotion.constant.BillingCycle;
import com.archmotion.constant.DiscountType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "subscription_plans")

@Setter
@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class SubscriptionPlan extends Auditable<UUID> {
    @Column(nullable = false, unique = true, length = 30)
    private String code;

    @Column(nullable = false, unique = true, length = 30)
    private String name;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private BillingCycle billingCycle;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private DiscountType discountType;

    @Column(precision = 12, scale = 2)
    private BigDecimal discountValue;
}
