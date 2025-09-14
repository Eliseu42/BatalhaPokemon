package com.batalhapokemon.model.enums;

public enum TipoPokemon {

    FOGO, AGUA, PLANTA, ELETRICO, VOADOR, NORMAL;

    public double calcularEfetividade(TipoPokemon defensor) {
        switch (this) {
            case FOGO:
                switch (defensor) {
                    case PLANTA:
                        return 2.0;
                    case AGUA:
                    case FOGO:
                        return 0.5;
                    default:
                        return 1.0;
                }
            case AGUA:
                switch (defensor) {
                    case FOGO:
                        return 2.0;
                    case PLANTA:
                    case AGUA:
                        return 0.5;
                    default:
                        return 1.0;
                }
            case PLANTA:
                switch (defensor) {
                    case AGUA:
                        return 2.0;
                    case FOGO:
                    case PLANTA:
                        return 0.5;
                    default:
                        return 1.0;
                }
            case ELETRICO:
                switch (defensor) {
                    case VOADOR:
                        return 2.0;
                    case ELETRICO:
                        return 0.5;
                    default:
                        return 1.0;
                }
            case VOADOR:
                switch (defensor) {
                    case PLANTA:
                        return 2.0;
                    case ELETRICO:
                        return 0.5;
                    default:
                        return 1.0;
                }
            case NORMAL:
                return 1.0; // Normal não tem efetividade especial contra nenhum tipo listado
            default:
                return 1.0;
        }
    }
}
