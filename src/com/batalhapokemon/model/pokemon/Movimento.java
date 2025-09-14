package com.batalhapokemon.model.pokemon;

import com.batalhapokemon.model.enums.TipoPokemon;

public class Movimento {
    private String nome;
    private TipoPokemon tipo;
    private double poder;
    private int precisao;
    private int pp;
    private int ppMaximo;
    private boolean isEspecial;

    public Movimento(String nome, TipoPokemon tipo, double poder, int precisao, int pp) {
        this.nome = nome;
        this.tipo = tipo;
        this.poder = poder;
        this.precisao = precisao;
        this.pp = pp;
        this.ppMaximo = pp;
        this.isEspecial = false;
    }

    public String getNome() {
        return nome;
    }

    public TipoPokemon getTipo() {
        return tipo;
    }

    public double getPoder() {
        return poder;
    }

    public int getPrecisao() {
        return precisao;
    }

    public int getPp() {
        return pp;
    }

    public int getPpMaximo() {
        return ppMaximo;
    }


    public boolean isEspecial() {
        return isEspecial;
    }

    public void setEspecial(boolean especial) {
        isEspecial = especial;
    }

    public double calcularDano(Pokemon atacante, Pokemon defensor) {
        // Verifica se o Pokémon atacante é válido (não nulo)
        if (atacante == null) {
            return 0.0; // Retorna 0 se não há atacante
        }

        // Verifica se o Pokémon defensor é válido (não nulo)
        if (defensor == null) {
            return 0.0; // Retorna 0 se não há defensor
        }

        // Verifica se o ataque ainda pode ser usado (tem PP disponível)
        if (!podeSerUsado()) {
            return 0.0; // Retorna 0 se o ataque não pode ser usado
        }

        // Calcula o dano base usando a fórmula: (poder do ataque * ataque do atacante) / defesa do defensor
        double danoBase = (poder * atacante.getAtaque()) / defensor.getDefesa();

        // Aplica bônus STAB (Same Type Attack Bonus) - 50% de bônus se o tipo do ataque for igual ao tipo do atacante
        if (tipo.equals(atacante.getTipo())) {
            danoBase *= 1.5; // Multiplica por 1.5 (adiciona 50% de bônus)
        }

        // Calcula a efetividade do tipo do ataque contra o tipo do defensor
        // (super efetivo = 2x, não muito efetivo = 0.5x, não afeta = 0x, normal = 1x)
        double efetividade = tipo.calcularEfetividade(defensor.getTipo());
        danoBase *= efetividade; // Aplica o multiplicador de efetividade

        // Adiciona uma variação aleatória ao dano (entre 85% e 100% do dano calculado)
        // Isso simula a variação natural dos ataques nos jogos Pokémon
        double variacao = 0.85 + (Math.random() * 0.15); // Gera um número entre 0.85 e 1.0
        danoBase *= variacao; // Aplica a variação aleatória

        return danoBase; // Retorna o dano final calculado
    }

    /**
     * Verifica se o ataque ainda pode ser usado (se tem PP restante)
     * @return true se o ataque pode ser usado, false caso contrário
     */
    public boolean podeSerUsado() {
        // Verifica se ainda há Power Points (PP) disponíveis para usar o ataque
        if (pp > 0) {
            return true; // Pode ser usado
        }
        return false; // Não pode ser usado (sem PP)
    }

    /**
     * Usa o ataque, diminuindo o PP em 1 unidade
     * Este método deve ser chamado toda vez que o ataque é executado
     */
    public void usar() {
        // Verifica se ainda há PP disponível antes de usar
        if (pp > 0) {
            pp--; // Diminui o PP em 1
        }
        // Se não há PP, não faz nada (ataque não pode ser usado)
    }

    /**
     * Restaura os Power Points do ataque para o valor máximo
     * Útil para itens como Éter ou ao visitar um Centro Pokémon
     */
    public void restaurarPP() {
        pp = ppMaximo; // Define o PP atual como o PP máximo
    }
}
