package other.activity.src.app_package

import other.WizardTemplateProviderImpl
import other.mvpAnnotation

fun mvpView(isKt: Boolean, provider: WizardTemplateProviderImpl) = if (isKt) mvpViewKt(provider) else mvpViewJava(provider)

private fun mvpViewKt(provider: WizardTemplateProviderImpl) = """
package ${provider.viewPackageName.value}


$mvpAnnotation

interface ${provider.pageName.value}View
constructor() : BaseView(){

}   
"""


fun mvpViewJava(provider: WizardTemplateProviderImpl) = """
package ${provider.viewPackageName.value};


$mvpAnnotation
public interface ${provider.pageName.value}View extends BaseView {
    
}   
"""