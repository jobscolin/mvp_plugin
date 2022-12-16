package other.activity.src.app_package

import other.WizardTemplateProviderImpl
import other.commonAnnotation

fun mvpActivity(isKt: Boolean, provider: WizardTemplateProviderImpl) = if (isKt) mvpActivityKt(provider) else mvpActivityJava(provider)

private fun mvpActivityKt(provider: WizardTemplateProviderImpl) = """
package ${provider.activityPackageName.value}

import kotlinx.android.synthetic.main.base_title.*

${commonAnnotation(provider)}
class ${provider.pageName.value}Activity : BaseActivity<Activity${provider.pageName.value}Binding, ${provider.pageName
    .value}Presenter>() , ${provider.pageName.value}View {
   
    override fun initView(){
       
    }
    
   
}
    
"""

private fun mvpActivityJava(provider: WizardTemplateProviderImpl) = """
package ${provider.activityPackageName.value};


${commonAnnotation(provider)}
public class ${provider.pageName.value}Activity extends BaseActivity<Activity${provider.pageName.value}Binding,${provider.pageName
    .value}Presenter> implements 
${provider.pageName.value}View {
    
    
    @Override
    protected void initView() {

    }
    
}
    
"""