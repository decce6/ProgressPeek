package me.decce.transformingbase.transform;

public enum TransformerDefinitions {
    ;

    public final TransformerDefinition definition;

    TransformerDefinitions(String target, Class<?> transformer) {
        this(new TransformerDefinition(target, transformer));
    }

    TransformerDefinitions(TransformerDefinition definition) {
        this.definition = definition;
    }
}
