package negocio;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class GerenciadoraContasTest {

	private ContaCorrente conta1;
	private ContaCorrente conta2;
	private ContaCorrente conta3;
	
	private List<ContaCorrente> contas;

	private GerenciadoraContas gerenciadora;
	
	@Before
	public void criarGerenciadoraClientes() {

		this.conta1 = new ContaCorrente(1, 1000.0, true);
		this.conta2 = new ContaCorrente(2, 2000.0, true);
		this.conta3 = new ContaCorrente(3, 500.0, false);
		
		this.contas = new ArrayList<ContaCorrente>();
		this.contas.add(conta1);
		this.contas.add(conta2);
		this.contas.add(conta3);
		
		this.gerenciadora = new GerenciadoraContas(contas);
		
	}
	
	@Test
	public void devemExistirTodosAsContas() {
	
		List<ContaCorrente> clientesGerenciadora = gerenciadora.getContasDoBanco();
		
		assertEquals(3, clientesGerenciadora.size());
		
		for(int i = 0; i < clientesGerenciadora.size(); i++) 
			assertEquals(contas.get(i).getId(), contas.get(i).getId());
		
	}
	
	@Test
	public void deveExistirContaComOID() {
		
		assertEquals(conta1, gerenciadora.pesquisaConta(conta1.getId()));
		
	}
	
	@Test
	public void deveTerAdicionadoConta() {
		
		List<ContaCorrente> contasGerenciadora = gerenciadora.getContasDoBanco();

		assertEquals(3, contasGerenciadora.size());
		
		ContaCorrente conta4 = new ContaCorrente(4, 850.0,true);
		gerenciadora.adicionaConta(conta4);
		
		assertEquals(4, contasGerenciadora.size());
		
		assertEquals(conta4, gerenciadora.pesquisaConta(conta4.getId()));
		
	}
	
	@Test
	public void deveTerRemovidoConta() {

		List<ContaCorrente> contasGerenciadora = gerenciadora.getContasDoBanco();

		assertEquals(3, contasGerenciadora.size());
		
		assertTrue(gerenciadora.removeConta(conta1.getId()));
		
		assertEquals(2, contasGerenciadora.size());
		
	}
	
	@Test
	public void deveExistirContaAtivaComId() {
		
		assertTrue(gerenciadora.contaAtiva(conta1.getId()));
		
	}
	
	@Test
	public void deveTerTransferidoValorEntreContas() {
		
		double valorEsperadoContaOrigem = conta1.getSaldo() - 200;
		double valorEsperadoContaDestino = conta2.getSaldo() + 200;
		
		assertTrue(gerenciadora.transfereValor(conta1.getId(), 200, conta2.getId()));
		
		assertEquals(valorEsperadoContaOrigem, conta1.getSaldo(), 0.0001);
		assertEquals(valorEsperadoContaDestino, conta2.getSaldo(), 0.0001);
		
	}

	@Test
	public void deveTerValorPositivoContaOrigem() {
		
		conta2.setSaldo(500);
		double valorMaiorQueConta2 = conta2.getSaldo() + 500;
		
		assertFalse(gerenciadora.transfereValor(conta2.getId(), valorMaiorQueConta2, conta3.getId()));
		
	}

}
