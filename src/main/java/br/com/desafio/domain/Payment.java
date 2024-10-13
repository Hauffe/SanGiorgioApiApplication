package br.com.desafio.domain;



import br.com.desafio.infrastructure.repository.SellerRepository;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Payment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @Column(name = "client_id", nullable = false)
    @JsonProperty("client_id")
    private String clientId;

    @OneToMany(mappedBy = "payment", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonProperty("payment_items")
    private List<PaymentItem> paymentItems;

    public boolean isValidPayment(SellerRepository sellerRepo) {
        return sellerRepo.existsByClientId(clientId);
    }
}
