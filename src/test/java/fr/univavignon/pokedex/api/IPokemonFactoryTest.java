package fr.univavignon.pokedex.api;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.function.ThrowingRunnable;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

public class IPokemonFactoryTest extends TestCase {
    @Mock
    IPokemonFactory mockPokemon = Mockito.mock(IPokemonFactory.class);

    Pokemon bulbi = new Pokemon(0, "Bulbizarre",126,126,90,613,64,4000,4,56);
    Pokemon aqua = new Pokemon(133, "Aquali",186,168,260,2729,202,5000,4,100);

    @Before
    public void setUp() {
        when(mockPokemon.createPokemon(0, 613, 64, 4000, 4)).thenReturn(bulbi);
        when(mockPokemon.createPokemon(133, 2729, 2020, 5000, 4)).thenReturn(aqua);
        // Mockito.doThrow(new PokedexException("Index out of bound")).when(mockPokemon).createPokemon(Mockito.intThat(i -> i < 0 || i > 150), anyInt(), anyInt(), anyInt(), anyInt());
    }

    @Test
    public void testCreatePokemon() throws PokedexException {
        assertEquals(bulbi, mockPokemon.createPokemon(0, 613, 64, 4000, 4));
    }
}