package rest;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.conference.ws.rest.entity.Talk;
import com.conference.ws.rest.modelentity.TalkBinding;
import com.conference.ws.rest.utility.Util;

class TalkTest {

	@Test
	public void test() {

		List<TalkBinding> listaBinding = datos(3);
		List<Talk> talks = Util.convertStringToTalk(listaBinding);

		Assertions.assertEquals(talks.size(), listaBinding.size());		
	}
	

	public List<TalkBinding> datos(int num) {

		List<TalkBinding> lista = new ArrayList<TalkBinding>();

		for (int i = num; i <= num; i++)
			lista.add(new TalkBinding("Talk " + i));
		return lista;
	}
}
