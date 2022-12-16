package other

import com.android.tools.idea.wizard.template.*
import java.io.File

/**
 * @author : xdl
 * @time : 2022/12/15
 * @describe :
 */
class WizardTemplateProviderImpl: WizardTemplateProvider() {
    override fun getTemplates(): List<Template> = listOf(
        //自定义模板就添加在此处
        mvpActivityTemplate,
        mvpFragmentTemplate
    )

    private val MIN_API = 16

    private val mvpActivityTemplate: Template
        get() = template {
            //revision = 2
            name = "Mvp Activity模版"
            description = "一键创建 MVP Activity单个页面所需要的全部组件"
            minApi = MIN_API
            //minBuildApi = MIN_API
            category = Category.Activity
            formFactor = FormFactor.Mobile
            screens = listOf(WizardUiContext.ActivityGallery, WizardUiContext.MenuEntry, WizardUiContext.NewProject, WizardUiContext.NewModule)
            thumb { File("template_blank_activity.png") }

            widgets(
                TextFieldWidget(pageName),
                PackageNameWidget(appPackageName),
                TextFieldWidget(activityLayoutName),
                CheckBoxWidget(generateActivityLayout),
                TextFieldWidget(activityPackageName),
                CheckBoxWidget(needPresenter),
                TextFieldWidget(presenterPackageName),
                CheckBoxWidget(needView),
                TextFieldWidget(viewPackageName),
                LanguageWidget()
            )

            //创建所需文件
            recipe = { te ->
                //val (projectData, srcOut, resOut) = te as ModuleTemplateData
                mvpActivityRecipe(this@WizardTemplateProviderImpl, (te as ModuleTemplateData))
            }
        }

    private val mvpFragmentTemplate: Template
        get() = template {
            //revision = 2
            name = "Mvp Fragment模版"
            description = "一键创建 MVP Fragment单个页面所需要的全部组件"
            minApi = MIN_API
            //minBuildApi = MIN_API
            category = Category.Fragment
            formFactor = FormFactor.Mobile
            screens = listOf(WizardUiContext.FragmentGallery, WizardUiContext.MenuEntry, WizardUiContext.NewProject, WizardUiContext
                .NewModule)
            thumb { File("template_blank_activity.png") }

            widgets(
                TextFieldWidget(pageName),
                PackageNameWidget(appPackageName),
                TextFieldWidget(fragmentLayoutName),
                CheckBoxWidget(generateFragmentLayout),
                TextFieldWidget(fragmentPackageName),
                CheckBoxWidget(needPresenter),
                TextFieldWidget(presenterPackageName),
                CheckBoxWidget(needView),
                TextFieldWidget(viewPackageName),
                LanguageWidget()
            )

            //创建所需文件
            recipe = { te ->
                //val (projectData, srcOut, resOut) = te as ModuleTemplateData
                mvpFragmentRecipe(this@WizardTemplateProviderImpl, (te as ModuleTemplateData))
            }
        }


    /** 新建页面名称 */
    val pageName = stringParameter {
        name = "Page Name"
        constraints = listOf(Constraint.UNIQUE, Constraint.NONEMPTY, Constraint.STRING)
        default = "Main"
        help = "请填写页面名,如填写 Main,会自动生成 MainActivity, MainPresenter 等文件"
    }


    /** 包名 */
    val appPackageName = stringParameter {
        name = "Package Name"
        constraints = listOf(Constraint.PACKAGE)
        default = "com.zdst.weex"
        help = "当前包名"
    }

    /** layout xml 文件 */
    val activityLayoutName = stringParameter {
        name = "Activity Layout Name"
        constraints = listOf(Constraint.LAYOUT, Constraint.NONEMPTY)
        suggest = { activityToLayout(pageName.value) }
        default = "activity_main"
        help = "Activity 创建之前需要填写 Activity 的布局名,若布局已创建就直接填写此布局名,若还没创建此布局,请勾选下面的单选框"
    }

    /** 是否需要 layout xml 文件 */
    val generateActivityLayout = booleanParameter {
        name = "Generate Activity Layout"
        default = true
        help = "是否需要给 Activity 生成布局? 若勾选,则使用上面的布局名给此 Activity 创建默认的布局"
    }

    /** Activity 路径 */
    val activityPackageName = stringParameter {
        name = "Ativity Package Name"
        constraints = listOf(Constraint.PACKAGE, Constraint.STRING)
        suggest = { appPackageName.value }
        default = appPackageName.value
        help = "Activity 将被输出到此包下,请认真核实此包名是否是你需要输出的目标包名"
    }

    /** Fragment xml 文件 */
    val fragmentLayoutName = stringParameter {
        name = "Fragment Layout Name"
        constraints = listOf(Constraint.LAYOUT, Constraint.NONEMPTY)
        suggest = { "fragment_${classToResource(pageName.value)}" }
        default = "fragment_main"
        help = "Fragment 创建之前需要填写 Fragment 的布局名,若布局已创建就直接填写此布局名,若还没创建此布局,请勾选下面的单选框"
    }

    /** 是否需要生成 Fragment layout 文件 */
    val generateFragmentLayout = booleanParameter {
        name = "Generate Fragment Layout"
        default = true
        help = "是否需要给 Fragment 生成布局? 若勾选,则使用上面的布局名给此 Fragment 创建默认的布局"
    }

    /** fragment 路径 */
    val fragmentPackageName = stringParameter {
        name = "Fragment Package Name"
        constraints = listOf(Constraint.PACKAGE, Constraint.STRING)
        suggest = { appPackageName.value }
        default = "appPackageName.value"
        help = "Fragment 将被输出到此包下,请认真核实此包名是否是你需要输出的目标包名"
    }


    val needPresenter = booleanParameter {
        name = "Generate Presenter"
        default = true
        help = "是否需要生成 Presenter ? 不勾选则不生成"
    }

    val presenterPackageName = stringParameter {
        name = "Presenter Package Name"
        constraints = listOf(Constraint.PACKAGE, Constraint.STRING)
        suggest = { appPackageName.value }
        default = appPackageName.value
        visible = { needPresenter.value }
        help = "Presenter 将被输出到此包下,请认真核实此包名是否是你需要输出的目标包名"
    }

    val needView = booleanParameter {
        name = "Generate View"
        default = true
        help = "是否需要生成 View ? 不勾选则不生成"
    }

    val viewPackageName = stringParameter {
        name = "View Package Name"
        constraints = listOf(Constraint.PACKAGE, Constraint.STRING)
        suggest = { appPackageName.value }
        default = appPackageName.value
        visible = { needView.value }
        help = "View 将被输出到此包下,请认真核实此包名是否是你需要输出的目标包名"
    }

}