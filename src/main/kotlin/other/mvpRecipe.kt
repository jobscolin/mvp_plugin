package other

//import other.activity.res.layout.simpleLayout
//import other.activity.src.app_package.*
import com.android.tools.idea.wizard.template.Language
import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import other.activity.res.layout.simpleLayout
import other.activity.src.app_package.mvpActivity
import other.activity.src.app_package.mvpPresenter
import other.activity.src.app_package.mvpView
import other.activity.src.mvpManifest
import other.fragment.src.app_package.mvpFragment
import java.io.File

fun RecipeExecutor.mvpActivityRecipe(provider: WizardTemplateProviderImpl, data: ModuleTemplateData) {
    mergeXml(mvpManifest(provider, data), File(data.manifestDir, "AndroidManifest.xml"))

    if (provider.generateActivityLayout.value) {
        save(simpleLayout(provider), File(data.resDir, "layout/${provider.activityLayoutName.value}.xml"))
    }

//    if (provider.needFragment.value && provider.generateFragmentLayout.value) {
//        save(simpleLayout(provider), File(data.resDir, "layout/${provider.fragmentLayoutName.value}.xml"))
//    }

    val languageSuffix = if (data.projectTemplateData.language == Language.Java) "java" else "kt"
    val isKt = data.projectTemplateData.language == Language.Kotlin

    val activityFile = File(
        data.rootDir,
        "${fFmSlashedPackageName(provider.activityPackageName.value)}/${provider.pageName.value}Activity.$languageSuffix"
    )
    save(mvpActivity(isKt, provider), activityFile)
    open(activityFile)

//    if (provider.needFragment.value) {
//        val fragmentFile = File(
//            data.rootDir,
//            "${fFmSlashedPackageName(provider.fragmentPackageName.value)}/${provider.pageName.value}Fragment.$languageSuffix"
//        )
//        save(armsFragment(isKt, provider), fragmentFile)
//        open(fragmentFile)
//    }

//
//    if (provider.needContract.value) {
//        val contractFile = File(
//            data.rootDir,
//            "${fFmSlashedPackageName(provider.contractPackageName.value)}/${provider.pageName.value}Contract.$languageSuffix"
//        )
//        save(armsContract(isKt, provider), contractFile)
//    }

    // 保存presenter
    if (provider.needPresenter.value) {
        val presenterFile = File(
            data.rootDir,
            "${fFmSlashedPackageName(provider.presenterPackageName.value)}/${provider.pageName.value}Presenter.$languageSuffix"
        )
        save(mvpPresenter(isKt, provider), presenterFile)
    }

    // 保存View
    if (provider.needView.value) {
        val viewFile = File(
            data.rootDir,
            "${fFmSlashedPackageName(provider.viewPackageName.value)}/${provider.pageName.value}View.$languageSuffix"
        )
        save(mvpView(isKt, provider), viewFile)
    }

//    if (provider.needDagger.value) {
//        val componentFile = File(
//            data.rootDir,
//            "${fFmSlashedPackageName(provider.componentPackageName.value)}/${provider.pageName.value}Component.$languageSuffix"
//        )
//        val moduleFile = File(
//            data.rootDir,
//            "${fFmSlashedPackageName(provider.moudlePackageName.value)}/${provider.pageName.value}Module.$languageSuffix"
//        )
//        save(armsComponent(isKt, provider), componentFile)
//        save(armsModule(isKt, provider), moduleFile)
//    }
}

fun RecipeExecutor.mvpFragmentRecipe(provider: WizardTemplateProviderImpl, data: ModuleTemplateData) {


    if (provider.generateFragmentLayout.value) {
        save(simpleLayout(provider), File(data.resDir, "layout/${provider.fragmentLayoutName.value}.xml"))
    }

    val languageSuffix = if (data.projectTemplateData.language == Language.Java) "java" else "kt"
    val isKt = data.projectTemplateData.language == Language.Kotlin

    val fragmentFile = File(
        data.rootDir,
        "${fFmSlashedPackageName(provider.fragmentPackageName.value)}/${provider.pageName.value}Fragment.$languageSuffix"
    )
    save(mvpFragment(isKt, provider), fragmentFile)
    open(fragmentFile)


    // 保存presenter
    if (provider.needPresenter.value) {
        val presenterFile = File(
            data.rootDir,
            "${fFmSlashedPackageName(provider.presenterPackageName.value)}/${provider.pageName.value}Presenter.$languageSuffix"
        )
        save(mvpPresenter(isKt, provider), presenterFile)
    }

    // 保存View
    if (provider.needView.value) {
        val viewFile = File(
            data.rootDir,
            "${fFmSlashedPackageName(provider.viewPackageName.value)}/${provider.pageName.value}View.$languageSuffix"
        )
        save(mvpView(isKt, provider), viewFile)
    }

}


fun fFmSlashedPackageName(oVar: String): String {
    return "src/main/java/${oVar.replace('.', '/')}"
}