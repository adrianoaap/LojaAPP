package vo;

import lombok.*;

import java.time.LocalDate;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RelatorioVendasVo {

    private String nomeProduto;
    private Long quantidadeVendida;
    private LocalDate dataUltimaVenda;

    public String toString(){
        return "# Nome: " + nomeProduto + " # Quantidade: " +  quantidadeVendida + " # Data: " +dataUltimaVenda;
    }

}
