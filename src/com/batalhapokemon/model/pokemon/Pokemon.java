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

    public Pokemon(String nome, int nivel, TipoPokemon tipo, double hp, double ataque, double defesa, double velocidade) {
        this.nome = nome;
        this.nivel = nivel;
        this.tipo = tipo;
        this.hp = hp;
        this.ataque = ataque;
        this.defesa = defesa;
        this.velocidade = velocidade;
    }

    public List<Movimento> getMovimentos() {
        return movimentos;
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

    public double getAtaque() {
        return ataque;
    }

    public double getDefesa() {
        return defesa;
    }

    public double getVelocidade() {
        return velocidade;
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
        if (quantidade < 0) {
            throw new PokemonException("A quantidade deve ser maior que zero.");
        }

        if (status == StatusPokemon.DESMAIADO) {
            return;
        }

        hp += quantidade;
        if (hp > hpMaximo) {
            hp = hpMaximo;
        }
    }

    public boolean estaDesmaiado() {
        if (status == StatusPokemon.DESMAIADO) {
            return true;
        }
        return false;
    }

    public void aplicarStatus(StatusPokemon novoStatus) {
        if (novoStatus == null) {
            throw new PokemonException("O novo status não pode ser nulo.");
        }

        if (status == StatusPokemon.DESMAIADO) {
            throw new PokemonException("O Pokémon está desmaiado e não pode receber status");
        }

        if (novoStatus == status) {
            return;
        }
        status = novoStatus;

        if (status == StatusPokemon.DESMAIADO) {
            hp = 0.0;
        }
    }
}
