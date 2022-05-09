package fr.univavignon.pokedex.api;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

public class IPokedexFactoryTest extends TestCase {

    @Mock
    IPokedexFactory pokedex = Mockito.mock(IPokedexFactory.class);

    @Before
    public void init() {

    }

    @Test
    public void testCreatePokedex() {
    }
}