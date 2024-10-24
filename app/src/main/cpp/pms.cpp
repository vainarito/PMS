// Write C++ code here.
//
// Do not forget to dynamically load the C++ library into your application.
//
// For instance,
//
// In MainActivity.java:
//    static {
//       System.loadLibrary("pms");
//    }
//
// Or, in MainActivity.kt:
//    companion object {
//      init {
//         System.loadLibrary("pms")
//      }
//    }

#include <jni.h>

extern "C"
[[maybe_unused]] JNIEXPORT jdoubleArray JNICALL
Java_com_appbsuirpms_pms_Activity_CartActivity_calculateCart(JNIEnv *env, jobject obj, jdouble totalFee, jboolean isCartEmpty) {
    double percentTax = 0.02;
    double delivery;

    if (isCartEmpty) {
        delivery = 0.0;
    } else {
        delivery = 10.0;
    }

    double tax = (double)((int)(totalFee * percentTax * 100)) / 100.0;
    double total = (double)((int)((totalFee + tax + delivery) * 100)) / 100.0;
    double itemTotal = (double)((int)(totalFee * 100)) / 100.0;

    // Создание массива для передачи значений обратно в Java (itemTotal, tax, delivery, total)
    jdoubleArray result = env->NewDoubleArray(4);
    if (result == NULL) {
        return NULL;
    }

    jdouble temp[4];
    temp[0] = itemTotal;
    temp[1] = tax;
    temp[2] = delivery;
    temp[3] = total;

    env->SetDoubleArrayRegion(result, 0, 4, temp);

    return result;
}

