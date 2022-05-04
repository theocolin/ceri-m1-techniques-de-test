package fr.univavignon.pokedex.api;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.function.ThrowingRunnable;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class IPokemonMetadataProviderTest {

    @Mock
    IPokemonMetadataProvider pokemonMetadataProvider = Mockito.mock(IPokemonMetadataProvider.class);
    PokemonMetadata bulbi = new PokemonMetadata(0,"Bulbizarre", 126, 126, 90);
    PokemonMetadata aqua = new PokemonMetadata(133,"Aquali", 186, 168, 260);

    @Before
    public void setUp() {
        try {
            when(pokemonMetadataProvider.getPokemonMetadata(0)).thenReturn(bulbi);
            when(pokemonMetadataProvider.getPokemonMetadata(133)).thenReturn(aqua);
            
            Mockito.doThrow(new PokedexException("Index out of bound")).when(pokemonMetadataProvider).getPokemonMetadata(Mockito.intThat(i -> i < 0 || i > 150));
        } catch (PokedexException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetPokemonMetadata() throws PokedexException {
        try {
            assertEquals(bulbi, pokemonMetadataProvider.getPokemonMetadata(0));
            assertThrows(PokedexException.class, (ThrowingRunnable) pokemonMetadataProvider.getPokemonMetadata(-1));
            assertThrows(PokedexException.class, (ThrowingRunnable) pokemonMetadataProvider.getPokemonMetadata(200));
        }catch (PokedexException e){
            e.printStackTrace();
        }
    }
}