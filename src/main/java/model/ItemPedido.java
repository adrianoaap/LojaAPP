package model;

import lombok.*;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.math.BigDecimal;

@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "itens_pedido")
public class ItemPedido {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "preco_unitario")
    private BigDecimal precoUnitario = BigDecimal.ZERO;

    private Integer quantidade;

    @ManyToOne(fetch = FetchType.EAGER)
    private Produto produto;

    @ManyToOne(fetch = FetchType.EAGER)
    private Pedido pedido;

    public BigDecimal getValor(){
        return precoUnitario.multiply(new BigDecimal(quantidade));
    }

    public ItemPedido(Integer quantidade, Pedido pedido, Produto produto) {
        this.quantidade = quantidade;
        this.pedido = pedido;
        this.produto = produto;
        this.precoUnitario = produto.getPreco();
    }

}
