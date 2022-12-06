package dao;

import model.Pedido;
import vo.RelatorioClientesVo;
import vo.RelatorioVendasVo;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.security.PublicKey;
import java.util.List;

public class PedidoDAO {
    private EntityManager entityManager;

    public PedidoDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public BigDecimal valorTotalVendido() {
        String jpql = "SELECT SUM(p.valorTotal) FROM Pedido p";
        return entityManager.createQuery(jpql, BigDecimal.class).getSingleResult();
    }

    public List<RelatorioVendasVo> relatorioVendas() {
        String jpql = "SELECT NEW vo.RelatorioVendasVo(" +
                "produto.nome, " +
                "SUM(item.quantidade), " +
                "MAX(pedido.data)) " +
                "FROM Pedido pedido " +
                "JOIN pedido.itemPedidos item " +
                "JOIN item.produto produto " +
                "GROUP BY produto.nome ";
        return entityManager.createQuery(jpql, RelatorioVendasVo.class).getResultList();
    }

    public Pedido buscarClientePorID(Integer id){
        return entityManager.createQuery("SELECT p FROM Pedido p JOIN FETCH p.cliente WHERE p.id =:id", Pedido.class)
                .setParameter("id", id)
                .getSingleResult();
    }


}
