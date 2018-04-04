package Teste;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Janelas.Tabuada;

public class TesteJogo {
	
	Tabuada obj;
	int r = 0;
	@Before
	public void setUp() throws Exception {
		obj = new Tabuada();
	}


	@Test
	public void test() 
	{
		
		for(int i=0;i<200;i++)
		{
			while(obj.progressBar.getValue()==98);
			obj.tfResposta.setText(String.valueOf(obj.valorA*obj.valorB));
			obj.btnVerificar.doClick();
		}
		assertEquals(199, obj.errosJogador+obj.acertos);
	}

}
