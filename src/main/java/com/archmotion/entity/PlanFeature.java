package com.archmotion.entity;

import com.archmotion.auditing.Auditable;
import com.archmotion.constant.LimitPeriod;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Entity
@Table(name = "plan_features")

@Setter
@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class PlanFeature extends Auditable<UUID> {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plan_id", nullable = false)
    private SubscriptionPlan plan;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "feature_id", nullable = false)
    private Feature feature;

    private Long usageLimit;

    @Enumerated(EnumType.STRING)
    private LimitPeriod limitPeriod;
}
