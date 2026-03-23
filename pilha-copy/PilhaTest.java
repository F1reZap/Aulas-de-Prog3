

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * A classe de teste PilhaTest.
 *
 * @author  (seu nome)
 * @version (um número da versão ou uma data)
 */
public class PilhaTest
{
    /**
     * Construtor default para a classe de teste PilhaTest
     */
    public PilhaTest()
    {
    }

    /**
     * Define a 'fixture' do teste.
     *
     * Chamado antes de cada método de caso de teste.
     */
    @BeforeEach
    public void setUp()
    {
    }

    /**
     * Desfaz a 'fixture' do teste.
     *
     * Chamado após cada método de teste de caso.
     */
    @AfterEach
    public void tearDown()
    {
    }
    
    @Test
    public void testPushPop() {
        Pilha p = new Pilha(2);
        p.push(1);
        p.push(2);
        assertEquals(2, p.pop());
        assertEquals(1, p.pop());
    }
    
    @Test
    public void testVazia() {
        Pilha p = new Pilha(2);
        assertTrue(p.isVazia());
        p.push(1);
        assertFalse(p.isVazia());
        p.push(2);
        p.pop();
        assertFalse(p.isVazia());
        p.pop();
        assertTrue(p.isVazia());
    }
    
    @Test
    public void testCheia() {
        Pilha p = new Pilha(2);
        assertFalse(p.isCheia());
        p.push(1);
        assertFalse(p.isCheia());
        p.push(2);
        assertTrue(p.isCheia());
        p.pop();
        assertFalse(p.isCheia());        
    }
    
    @Test
    public void testPeek() {
        Pilha p = new Pilha(2);
        p.push(1);
        assertEquals(1, p.peek());
        assertEquals(1, p.pop());
    }
    
    @Test
    public void testOverflow() {
        Pilha p = new Pilha(2);
        p.push(1);
        p.push(2);
        try {
            p.push(3);
            throw new Exception();
        } catch (IllegalStateException e) {
            assertEquals("stack overflow", e.getMessage());
        } catch (Exception e) {
            fail("Esperado lançamento de IllegalStateException");            
        }
    }

    @Test
    public void testUnderflow() {
        Pilha p = new Pilha(2);
        p.push(1);
        p.push(2);
        p.pop();
        p.pop();
        try {
            p.pop();
            throw new Exception();
        } catch (IllegalStateException e) {
            assertEquals("stack underflow", e.getMessage());
        } catch (Exception e) {
            fail("Esperado lançamento de IllegalStateException");            
        }
    }
}
