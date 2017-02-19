#include <jni.h>
#include <string>

using namespace std;

extern "C"
jstring
Java_xyz_lirongsansy_projectdemeter_Initial_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
}

jdouble Java_xyz_lirongsansy_projectdemeter_Information_getBMR(JNIEnv *env, jobject obj, jdouble height, jdouble weight, jint age, jint gender) {
    double bmr, modh, modw, modAge;
    if(gender == 0){
        modw = 13.397 * weight;
        modh = 4.799 * height;
        modAge = 5.677 * age;
        bmr = (88.362 + modh + modw - modAge);
        return bmr;
    }
    else{
        modw = 9.247 * weight;
        modh = 3.098 * height;
        modAge = 4.330 * age;
        bmr = (447.593 + modw + modh - modAge);
    }
    return bmr;
}
