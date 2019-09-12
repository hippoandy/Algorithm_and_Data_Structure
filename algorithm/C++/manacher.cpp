#include <iostream>
#include <cstring>
using namespace std;
 
char* getSubstring(char c[], int starting, int ending){
 
    int length = ending - starting + 1;
    int temp = length;

    if(ending < starting){
        return "null";
    }

    if(length > strlen(c)){
        return "null";
    }

    char* str = new char[length + 1];

    char *p1 = str;
    char *p2 = &c[starting];

    while(temp--){
        *(p1++) = *(p2++);
    }

    *p1 = '\0';

    return str;
}
 
char* preProcess(char c[])
{
    int length = strlen(c);

    char* str = new char[2 * strlen(c) + 1];
    char* temp = str;
 
    *(temp++) = '#';
    for(int i = 0; i < length; i++){
        *(temp++) = c[i];
        *(temp++) = '#';
    }

    *temp = '\0';

    return str;
}
 
char* postProcess(char c[])
{
    int length = strlen(c);

    char* str = new char[length];
    char* temp = str;

    for(int i = 0; i < length; i++){
        if(c[i] != '#'){
            *(temp++) = c[i];
        }
    }
    *temp = '\0';

    return str;
}
 
int main()
{
    char* str = preProcess("jklollolkidding");
    int length = strlen(str);

    int *P = new int[length];

    int C = 0;
    int R = 0;

    for(int i = 0; i < length; i++)
    {
        int i_mirror = C - (i - C);

        if(R > i){
            P[i] = min(R - i, P[i_mirror]);
        }else{
            P[i] = 0;
        }

        while (str[i + 1 + P[i]] == str[i - 1 - P[i]]){
            P[i]++;
        }

        if (i + P[i] > R) {
          C = i;
          R = i + P[i];
        }
    }

    int maxLen = 0;
    int centerIndex = 0;
    for (int i = 1; i < length - 1; i++) {
        if (P[i] > maxLen) {
            maxLen = P[i];
            centerIndex = i;
        }
    }

    cout << postProcess(getSubstring(str, centerIndex - maxLen, centerIndex + maxLen)) << endl;
    return 0;
}