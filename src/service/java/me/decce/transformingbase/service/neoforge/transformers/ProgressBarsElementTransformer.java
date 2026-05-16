package me.decce.transformingbase.service.neoforge.transformers;

//? neoforge {
/*import me.decce.transformingbase.core.ProgressPeekCore;
import me.decce.transformingbase.core.ProgressStatus;
import net.lenni0451.classtransform.annotations.CTarget;
import net.lenni0451.classtransform.annotations.CTransformer;
import net.lenni0451.classtransform.annotations.injection.CInject;
import net.lenni0451.classtransform.annotations.injection.CModifyExpressionValue;
import net.neoforged.fml.earlydisplay.render.elements.ProgressBarsElement;
import net.neoforged.fml.loading.progress.ProgressMeter;

import java.util.List;

@SuppressWarnings("UnstableApiUsage")
@CTransformer(ProgressBarsElement.class)
public class ProgressBarsElementTransformer {
    @CModifyExpressionValue(method = "render", target = @CTarget(value = "INVOKE", target = "Lnet/neoforged/fml/loading/progress/StartupNotificationManager;getCurrentProgress()Ljava/util/List;"))
    private List<ProgressMeter> progresspeek$updateProgress(List<ProgressMeter> list) {
        // Avoid calling StartupNotificationManager$getCurrentProgress again because it involves synchronization
        if (list.size() > 0) {
            var progress = list.get(0);
            if (progress.steps() == 0) {
                ProgressPeekCore.setStatus(ProgressStatus.INDETERMINATE);
            }
            else {
                ProgressPeekCore.setStatus(ProgressStatus.NORMAL);
                ProgressPeekCore.setValue(progress.progress());
            }
        }
        return list;
    }
}
*///? }
