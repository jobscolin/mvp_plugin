package other.fragment.src.app_package

import other.WizardTemplateProviderImpl
import other.commonAnnotation

fun mvpFragment(isKt: Boolean, provider: WizardTemplateProviderImpl) = if (isKt) mvpFragmentKt(provider) else mvpFragmentJava(provider)

private fun mvpFragmentKt(provider: WizardTemplateProviderImpl) = """
package ${provider.fragmentPackageName.value}

import kotlinx.android.synthetic.main.base_title.*

${commonAnnotation(provider)}
class ${provider.pageName.value}Fragment : BaseFragment<Fragment${provider.pageName.value}Binding, ${provider.pageName
    .value}Presenter>() , ${provider.pageName.value}View{
    companion object {
    fun newInstance():${provider.pageName.value}Fragment {
        val fragment = ${provider.pageName.value}Fragment()
        return fragment
    }
    }
    
    override fun initView(){

    }
    
}
    
"""


fun mvpFragmentJava(provider: WizardTemplateProviderImpl) = """
package ${provider.fragmentPackageName.value};


${commonAnnotation(provider)}
class ${provider.pageName.value}Fragment extends BaseFragment<Fragment${provider.pageName.value}Binding, ${provider.pageName
    .value}Presenter> implements ${provider.pageName.value}View{
    
    public static ${provider.pageName.value}Fragment newInstance() {
        ${provider.pageName.value}Fragment fragment = new ${provider.pageName.value}Fragment();
        return fragment;
    }
    
    @Override
    public View initView(){
    
    }
    
}
    
"""