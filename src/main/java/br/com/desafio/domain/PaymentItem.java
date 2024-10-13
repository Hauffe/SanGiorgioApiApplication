package br.com.desafio.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentItem implements Serializable {

    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "payment_id", nullable = false)
    @JsonProperty("payment_id")
    private String paymentId;

    @Column(name = "payment_value", nullable = false)
    @JsonProperty("payment_value")
    private BigDecimal paymentValue;

    @Enumerated(EnumType.STRING)
    @JsonProperty("payment_status")
    private PaymentStatus paymentStatus;

    @ManyToOne
    @JoinColumn(name = "payment_id", insertable = false, updatable = false)
    private Payment payment;
}
