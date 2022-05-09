package fr.univavignon.pokedex.api;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class IPokemonTrainerFactoryTest {
    @Mock
    IPokemonTrainerFactory pokemonTrainerFactoryMock = Mockito.mock(IPokemonTrainerFactory.class);
    @Mock
    IPokedexFactory pokedexFactoryMock = Mockito.mock(IPokedexFactory.class);
    @Mock
    IPokedex pokedexMock = Mockito.mock(IPokedex.class);
    PokemonTrainer pokemonTrainer = new PokemonTrainer("Theo", Team.INSTINCT, pokedexMock);

    @Before
    public void setUp() {
        when(pokemonTrainerFactoryMock.createTrainer("Theo", Team.INSTINCT, pokedexFactoryMock)).thenReturn(pokemonTrainer);
    }

    @Test
    public void testCreateTrainer() {
        assertEquals(pokemonTrainer, pokemonTrainerFactoryMock.createTrainer("Theo", Team.INSTINCT, pokedexFactoryMock));
    }
}