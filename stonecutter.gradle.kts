plugins {
    id("dev.kikugie.stonecutter")
}

stonecutter active "1.21.11-fabric"

stonecutter parameters {
    constants.match(node.metadata.project.substringAfterLast('-'), "fabric", "neoforge", "forge")
    replacements.string(current.parsed >= "26.1") {
        replace("GuiGraphics", "GuiGraphicsExtractor")
        replace("\"render\"", "\"extractRenderState\"")
        replace("net.minecraft.client.gui.render.state", "net.minecraft.client.renderer.state.gui")
    }
}

tasks.register("publishAll") {
    group = "publishing"
    dependsOn(stonecutter.tasks.named("publishMods"))
}

tasks.register("publishAllModrinth") {
    group = "publishing"
    dependsOn(stonecutter.tasks.named("publishModrinth"))
}

tasks.register("publishAllCurseForge") {
    group = "publishing"
    dependsOn(stonecutter.tasks.named("publishCurseforge"))
}

stonecutter.tasks {
    order("publishMods")
    order("publishModrinth")
    order("publishCurseforge")
}

