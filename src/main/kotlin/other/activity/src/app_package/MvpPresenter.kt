package other.activity.src.app_package

import other.WizardTemplateProviderImpl
import other.mvpAnnotation

fun mvpPresenter(isKt: Boolean, provider: WizardTemplateProviderImpl): String = if (isKt) mvpPresenterKt(provider) else
    mvpPresenterJava(provider)

private fun mvpPresenterKt(provider: WizardTemplateProviderImpl) = """
package ${provider.presenterPackageName.value}



$mvpAnnotation

class ${provider.pageName.value}Presenter

constructor() :BasePresenter<${provider.pageName.value}View>() {
    
}   
"""


fun mvpPresenterJava(provider: WizardTemplateProviderImpl) = """
package ${provider.presenterPackageName.value};


$mvpAnnotation
public class ${provider.pageName.value}Presenter extends BasePresenter<${provider.pageName.value}View>{
    
}   
"""