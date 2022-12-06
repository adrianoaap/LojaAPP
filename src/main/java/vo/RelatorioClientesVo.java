package vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RelatorioClientesVo {

    private String nomeCliente;
    private String nomeProduto;
    private BigDecimal precoProduto;


    public String toString() {
        return "# Cliente: " + nomeCliente + " # Produto: " + nomeProduto + " # Preço R$: " + precoProduto;
    }

}
