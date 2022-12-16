package other

import java.text.SimpleDateFormat
import java.util.*

fun commonAnnotation(provider: WizardTemplateProviderImpl) = """
/**
 * @author : xdl
 * @time : 2022/12/15
 * @describe : ${SimpleDateFormat("yyyy/MM/dd ").format(Date(System.currentTimeMillis()))}
 */
""".trimIndent()

val mvpAnnotation = """
/**
 * @author : xdl
 * @time : 2022/12/15
 * @describe : ${SimpleDateFormat("yyyy/MM/dd ").format(Date(System.currentTimeMillis()))}
 */
""".trimIndent()