package negocio;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class GerenciadoraClientesTest {
	
	private Cliente joao;
	private Cliente maria;
	private Cliente carlos;
	
	private List<Cliente> clientes;

	private GerenciadoraClientes gerenciadora;
	
	@Before
	public void criarGerenciadoraClientes() {

		this.joao = new Cliente(1, "Joao", 19, "joao@gmail.com", 1, true);
		this.maria = new Cliente(2, "Maria", 25, "maria@gmail.com", 2, true);
		this.carlos = new Cliente(3, "Carlos", 68, "carlos@gmail.com", 3, false);
		
		this.clientes = new ArrayList<Cliente>();
		this.clientes.add(joao);
		this.clientes.add(maria);
		this.clientes.add(carlos);
		
		this.gerenciadora = new GerenciadoraClientes(clientes);
		
	}
	
	@Test
	public void deveTerAdicionadoCliente() {
		
		List<Cliente> clientesGerenciadora = gerenciadora.getClientesDoBanco();

		assertEquals(3, clientesGerenciadora.size());
		
		Cliente abilio = new Cliente(4, "Abilio", 36, "abilio@gmail.com", 4, true);
		gerenciadora.adicionaCliente(abilio);
		
		assertEquals(4, clientesGerenciadora.size());
		
		assertEquals(abilio, gerenciadora.pesquisaCliente(abilio.getId()));
		
	}
	
	@Test
	public void devemExistirTodosOsClientes() {
	
		List<Cliente> clientesGerenciadora = gerenciadora.getClientesDoBanco();
		
		assertEquals(3, clientesGerenciadora.size());
		
		for(int i = 0; i < clientesGerenciadora.size(); i++) 
			assertEquals(clientes.get(i).getId(), clientes.get(i).getId());
		
	}
	
	@Test
	public void deveExistirClienteComOID() {
		
		assertEquals(joao, gerenciadora.pesquisaCliente(joao.getId()));
		
	}
	
	@Test
	public void deveTerRemovidoCliente() {

		List<Cliente> clientesGerenciadora = gerenciadora.getClientesDoBanco();

		assertEquals(3, clientesGerenciadora.size());
		
		assertTrue(gerenciadora.removeCliente(joao.getId()));
		
		assertEquals(2, clientesGerenciadora.size());
		
	}
	
	@Test
	public void deveExistirClienteAtivoComId() {
		
		assertTrue(gerenciadora.clienteAtivo(joao.getId()));
		
	}
	
	@Test
	public void deveTerLimpadoAListaDeClientes() {
		
		List<Cliente> clientesGerenciadora = gerenciadora.getClientesDoBanco();

		assertEquals(3, clientesGerenciadora.size());
		
		gerenciadora.limpa();
		
		assertEquals(0, clientesGerenciadora.size());
		
	}
	
	@Test()
	public void naoDeveTerIdadeDentroDoIntervaloValido() {
		
		try {
			carlos.setIdade(68);
			gerenciadora.validaIdade(carlos.getIdade());
			fail();
		} catch (IdadeNaoPermitidaException e) {
			
		}
	}

}
