package fr.univavignon.pokedex.api;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;

public class IPokedexFactoryTest extends TestCase {

    @Mock
    IPokedexFactory pokedex = Mockito.mock(IPokedexFactory.class);
    @Mock
    IPokemonFactory iPokemonFactoryMock = Mockito.mock(IPokemonFactory.class);
    @Mock
    IPokemonMetadataProvider iPokemonMetadataProviderMock = Mockito.mock(IPokemonMetadataProvider.class);
    @Mock
    IPokedex iPokedexMock = Mockito.mock(IPokedex.class);


    @Test
    public void testCreatePokedex() {
        when(pokedex.createPokedex(iPokemonMetadataProviderMock, iPokemonFactoryMock)).thenReturn(iPokedexMock);
        assertEquals(iPokedexMock, pokedex.createPokedex(iPokemonMetadataProviderMock, iPokemonFactoryMock));
    }
}