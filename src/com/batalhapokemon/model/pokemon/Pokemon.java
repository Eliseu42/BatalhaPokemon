package com.batalhapokemon.model.pokemon;

import com.batalhapokemon.exception.PokemonException;
import com.batalhapokemon.model.enums.StatusPokemon;
import com.batalhapokemon.model.enums.TipoPokemon;

import java.util.List;

public abstract class Pokemon {

    private String nome;
    private int nivel;
    private double hp;
    private double hpMaximo;
    private double ataque;
    private double defesa;
    private double velocidade;
    private TipoPokemon tipo;
    private StatusPokemon status;
    private List<Movimento> movimentos;

    public Pokemon(String nome, int nivel, TipoPokemon tipo) {
        this.nome = nome;
        this.nivel = nivel;
        this.tipo = tipo;
    }

    public double getAtaque() {
        return ataque;
    }

    public double getDefesa() {
        return defesa;
    }

    public String getNome() {
        return nome;
    }

    public int getNivel() {
        return nivel;
    }

    public double getHp() {
        return hp;
    }

    public TipoPokemon getTipo() {
        return tipo;
    }

    public void receberDano(double dano) {
        if (dano < 0) {
            throw new PokemonException("O dano deve ser maior que zero.");
        }

        if (status == StatusPokemon.DESMAIADO) {
            return;
        }

        hp -= dano;

        if (hp < 0) {
            hp = 0;
        }

        if (Math.abs(hp) < 0.0001) {
            status = StatusPokemon.DESMAIADO;
        }
    }

    public void curar(double quantidade) {
    }

    public boolean estaDesmaiado() {
        return false;
    }

    public void adicionarMovimento (Movimento movimento) {

    }

    public List<Movimento> getMovimentosDisponiveis() {
        return null;
    }

    public void aplicarStatus(StatusPokemon novoStatus) {

    }
}
