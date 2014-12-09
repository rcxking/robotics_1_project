#include <string.h>
#include "adjustablePIDcontrol_3.h"
#ifdef CATSDLL_EXPORTS
#define CATSDLL_API                    __declspec(dllexport)
#else
#define CATSDLL_API                    __declspec(dllimport)
#endif

CATSDLL_API int SetModelInput(int ind, double* data, int len)
{
  if (ind==(1)) {
    if (len !=6)
      return -1;
    memcpy(&adjustablePIDcontrol_3_U.JointAngles,data,6*sizeof(double));
    return 1;
  }

  if (ind==(2)) {
    if (len !=16)
      return -1;
    memcpy(&adjustablePIDcontrol_3_U.ExternalInput,data,16*sizeof(double));
    return 1;
  }

  return -1;
}

CATSDLL_API int GetModelOutput(int ind, double* data, int len)
{
  if (ind==(1)) {
    if (len !=3)
      return -1;
    memcpy(data,&adjustablePIDcontrol_3_Y.JointTorques,3*sizeof(double));
    return 1;
  }

  if (ind==(2)) {
    if (len !=32)
      return -1;
    memcpy(data,&adjustablePIDcontrol_3_Y.ExtraOutput,32*sizeof(double));
    return 1;
  }

  return 1;
}
