package br.com.rickgurgel.jms;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class JmsApplicationTests {
	
	@Autowired
	private Sender sender;
	
	@Autowired
	private Receiver receiver;
	
	@Test
	public void testReceive() throws Exception {
		String[] s = {"r", "i", "c", "a", "r", "d", "o"," ", "g", "u", "r", "g", "e", "l"};
		for(int i=0; i < s.length; i++) {
			sender.send("order: "+ s[i]);
		}
		
		receiver.getLatch().await(3500, TimeUnit.MILLISECONDS);
		assertThat(receiver.getLatch().getCount()).isEqualTo(0);
	}
}
