package fr.univavignon.pokedex.api;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.function.ThrowingRunnable;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

public class IPokedexTest extends TestCase {

    IPokedex iPokedexMock;

    @Before
    public void setUp() {
        iPokedexMock = Mockito.mock(IPokedex.class);
    }

    @Test
    public void testSize() {
        when(iPokedexMock.size()).thenReturn(22);
        assertEquals(22,iPokedexMock.size());
    }

    @Test
    public void testAddPokemon() {
        Pokemon pokemon = new Pokemon(0,"Bulbizarre",126,126,90,613,64,4000,4,56);
        when(iPokedexMock.addPokemon(pokemon)).thenReturn(22);
        assertEquals(22,iPokedexMock.addPokemon(pokemon));
    }

    @Test
    public void testGetPokemon() throws PokedexException {
        Pokemon pokemon = new Pokemon(0,"Bulbizarre",126,126,90,613,64,4000,4,56);
        when(iPokedexMock.getPokemon(anyInt())).thenAnswer(input -> {
            int index = input.getArgument(0);
            if(index > 150 || index < 0) {
                throw new PokedexException("Index out of range");
            }
            else {
                return pokemon;
            }
        });
        assertEquals(pokemon,iPokedexMock.getPokemon(22));
        assertThrows(PokedexException.class,()->iPokedexMock.getPokemon(-1));
        assertThrows(PokedexException.class,()->iPokedexMock.getPokemon(151));
    }

    @Test
    public void testGetPokemons() {
        List<Pokemon> pokemons = new ArrayList<>();
        pokemons.add(new Pokemon(0,"Bulbizarre",126,126,90,613,64,4000,4,56));
        pokemons.add(new Pokemon(1,"Aquali",186,168,260,2729,202,5000,4,100));

        when(iPokedexMock.getPokemons()).thenReturn(pokemons);

        assertEquals(pokemons,iPokedexMock.getPokemons());
    }

    @Test
    public void testGetPokemonsSorted() {
        Pokemon aquali = new Pokemon(0, "Aquali", 186, 168, 260, 2729, 202, 5000, 4, 100);
        Pokemon bulbizarre = new Pokemon(1, "Bulbizarre", 126, 126, 90, 613, 64, 4000, 4, 56);

        when(iPokedexMock.getPokemons(any())).thenAnswer(input -> {
            Comparator<Pokemon> order = input.getArgument(0);
            List<Pokemon> pokemons = new ArrayList<Pokemon>();
            if(order == PokemonComparators.NAME) {
                pokemons.add(aquali);
                pokemons.add(bulbizarre);
                return pokemons;
            } else if(order == PokemonComparators.CP) {
                pokemons.add(aquali);
                pokemons.add(bulbizarre);
                return pokemons;
            } else {
                pokemons.add(bulbizarre);
                pokemons.add(aquali);
                return pokemons;
            }
        });

        List<Pokemon> sortedPokemons = new ArrayList<Pokemon>();
        sortedPokemons.add(aquali);
        sortedPokemons.add(bulbizarre);
        assertEquals(sortedPokemons.get(0).getName(), iPokedexMock.getPokemons(PokemonComparators.NAME).get(0).getName());

        sortedPokemons.clear();
        sortedPokemons.add(aquali);
        sortedPokemons.add(bulbizarre);
        assertEquals(sortedPokemons.get(0).getCp(), iPokedexMock.getPokemons(PokemonComparators.CP).get(0).getCp());

        sortedPokemons.clear();
        sortedPokemons.add(bulbizarre);
        sortedPokemons.add(aquali);
        assertEquals(sortedPokemons.get(0).getIndex(), iPokedexMock.getPokemons(PokemonComparators.INDEX).get(0).getIndex());

    }
}