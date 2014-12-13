/*
 * adjustablePIDcontrol_3.c
 *
 * Code generation for model "adjustablePIDcontrol_3".
 *
 * Model version              : 1.39
 * Simulink Coder version : 8.2 (R2012a) 29-Dec-2011
 * C source code generated on : Tue Dec 09 15:06:25 2014
 *
 * Target selection: catsdll.tlc
 * Note: GRT includes extra infrastructure and instrumentation for prototyping
 * Embedded hardware selection: 32-bit Generic
 * Code generation objectives: Unspecified
 * Validation result: Not run
 */
#include "adjustablePIDcontrol_3.h"
#include "adjustablePIDcontrol_3_private.h"

/* Block signals (auto storage) */
BlockIO_adjustablePIDcontrol_3 adjustablePIDcontrol_3_B;

/* Block states (auto storage) */
D_Work_adjustablePIDcontrol_3 adjustablePIDcontrol_3_DWork;

/* External inputs (root inport signals with auto storage) */
ExternalInputs_adjustablePIDcon adjustablePIDcontrol_3_U;

/* External outputs (root outports fed by signals with auto storage) */
ExternalOutputs_adjustablePIDco adjustablePIDcontrol_3_Y;

/* Real-time model */
RT_MODEL_adjustablePIDcontrol_3 adjustablePIDcontrol_3_M_;
RT_MODEL_adjustablePIDcontrol_3 *const adjustablePIDcontrol_3_M =
  &adjustablePIDcontrol_3_M_;

/* Model output function */
static void adjustablePIDcontrol_3_output(void)
{
  int32_T cff;
  real_T acc;
  int32_T j;
  real_T rtb_CommandedJointTorques_idx;
  real_T rtb_CommandedJointTorques_idx_0;

  /* Sum: '<Root>/Sum' incorporates:
   *  Inport: '<Root>/External Input'
   *  Inport: '<Root>/Joint Angles'
   */
  adjustablePIDcontrol_3_B.Error[0] = adjustablePIDcontrol_3_U.ExternalInput[0]
    - adjustablePIDcontrol_3_U.JointAngles[0];
  adjustablePIDcontrol_3_B.Error[1] = adjustablePIDcontrol_3_U.ExternalInput[1]
    - adjustablePIDcontrol_3_U.JointAngles[1];
  adjustablePIDcontrol_3_B.Error[2] = adjustablePIDcontrol_3_U.ExternalInput[2]
    - adjustablePIDcontrol_3_U.JointAngles[2];

  /* DiscreteFir: '<Root>/Discrete FIR Filter' */
  acc = adjustablePIDcontrol_3_B.Error[0] *
    adjustablePIDcontrol_3_P.DiscreteFIRFilter_Coefficients[0];
  cff = 1;
  for (j = adjustablePIDcontrol_3_DWork.DiscreteFIRFilter_circBuf; j < 5; j++) {
    acc += adjustablePIDcontrol_3_DWork.DiscreteFIRFilter_states[j] *
      adjustablePIDcontrol_3_P.DiscreteFIRFilter_Coefficients[cff];
    cff++;
  }

  for (j = 0; j < adjustablePIDcontrol_3_DWork.DiscreteFIRFilter_circBuf; j++) {
    acc += adjustablePIDcontrol_3_DWork.DiscreteFIRFilter_states[j] *
      adjustablePIDcontrol_3_P.DiscreteFIRFilter_Coefficients[cff];
    cff++;
  }

  rtb_CommandedJointTorques_idx = acc;
  acc = adjustablePIDcontrol_3_B.Error[1] *
    adjustablePIDcontrol_3_P.DiscreteFIRFilter_Coefficients[0];
  cff = 1;
  for (j = adjustablePIDcontrol_3_DWork.DiscreteFIRFilter_circBuf; j < 5; j++) {
    acc += adjustablePIDcontrol_3_DWork.DiscreteFIRFilter_states[5 + j] *
      adjustablePIDcontrol_3_P.DiscreteFIRFilter_Coefficients[cff];
    cff++;
  }

  for (j = 0; j < adjustablePIDcontrol_3_DWork.DiscreteFIRFilter_circBuf; j++) {
    acc += adjustablePIDcontrol_3_DWork.DiscreteFIRFilter_states[5 + j] *
      adjustablePIDcontrol_3_P.DiscreteFIRFilter_Coefficients[cff];
    cff++;
  }

  rtb_CommandedJointTorques_idx_0 = acc;
  acc = adjustablePIDcontrol_3_B.Error[2] *
    adjustablePIDcontrol_3_P.DiscreteFIRFilter_Coefficients[0];
  cff = 1;
  for (j = adjustablePIDcontrol_3_DWork.DiscreteFIRFilter_circBuf; j < 5; j++) {
    acc += adjustablePIDcontrol_3_DWork.DiscreteFIRFilter_states[10 + j] *
      adjustablePIDcontrol_3_P.DiscreteFIRFilter_Coefficients[cff];
    cff++;
  }

  for (j = 0; j < adjustablePIDcontrol_3_DWork.DiscreteFIRFilter_circBuf; j++) {
    acc += adjustablePIDcontrol_3_DWork.DiscreteFIRFilter_states[10 + j] *
      adjustablePIDcontrol_3_P.DiscreteFIRFilter_Coefficients[cff];
    cff++;
  }

  /* SampleTimeMath: '<S1>/TSamp' incorporates:
   *  DiscreteFir: '<Root>/Discrete FIR Filter'
   *
   * About '<S1>/TSamp':
   *  y = u * K where K = 1 / ( w * Ts )
   */
  adjustablePIDcontrol_3_B.TSamp[0] = rtb_CommandedJointTorques_idx *
    adjustablePIDcontrol_3_P.TSamp_WtEt;
  adjustablePIDcontrol_3_B.TSamp[1] = rtb_CommandedJointTorques_idx_0 *
    adjustablePIDcontrol_3_P.TSamp_WtEt;
  adjustablePIDcontrol_3_B.TSamp[2] = acc * adjustablePIDcontrol_3_P.TSamp_WtEt;

  /* Sum: '<Root>/Sum1' incorporates:
   *  DiscreteIntegrator: '<Root>/Discrete-Time Integrator'
   *  Inport: '<Root>/External Input'
   *  Product: '<Root>/Kd'
   *  Product: '<Root>/Ki'
   *  Product: '<Root>/Kp'
   *  Sum: '<S1>/Diff'
   *  UnitDelay: '<S1>/UD'
   */
  rtb_CommandedJointTorques_idx = ((adjustablePIDcontrol_3_B.TSamp[0] -
    adjustablePIDcontrol_3_DWork.UD_DSTATE[0]) *
    adjustablePIDcontrol_3_U.ExternalInput[6] + adjustablePIDcontrol_3_B.Error[0]
    * adjustablePIDcontrol_3_U.ExternalInput[3]) +
    adjustablePIDcontrol_3_DWork.DiscreteTimeIntegrator_DSTATE[0] *
    adjustablePIDcontrol_3_U.ExternalInput[9];
  rtb_CommandedJointTorques_idx_0 = ((adjustablePIDcontrol_3_B.TSamp[1] -
    adjustablePIDcontrol_3_DWork.UD_DSTATE[1]) *
    adjustablePIDcontrol_3_U.ExternalInput[7] + adjustablePIDcontrol_3_B.Error[1]
    * adjustablePIDcontrol_3_U.ExternalInput[4]) +
    adjustablePIDcontrol_3_DWork.DiscreteTimeIntegrator_DSTATE[1] *
    adjustablePIDcontrol_3_U.ExternalInput[10];
  acc = ((adjustablePIDcontrol_3_B.TSamp[2] -
          adjustablePIDcontrol_3_DWork.UD_DSTATE[2]) *
         adjustablePIDcontrol_3_U.ExternalInput[8] +
         adjustablePIDcontrol_3_B.Error[2] *
         adjustablePIDcontrol_3_U.ExternalInput[5]) +
    adjustablePIDcontrol_3_DWork.DiscreteTimeIntegrator_DSTATE[2] *
    adjustablePIDcontrol_3_U.ExternalInput[11];

  /* Outport: '<Root>/Joint Torques' */
  adjustablePIDcontrol_3_Y.JointTorques[0] = rtb_CommandedJointTorques_idx;
  adjustablePIDcontrol_3_Y.JointTorques[1] = rtb_CommandedJointTorques_idx_0;
  adjustablePIDcontrol_3_Y.JointTorques[2] = acc;

  /* Outport: '<Root>/Extra Output' incorporates:
   *  Constant: '<Root>/Buffer'
   *  Inport: '<Root>/External Input'
   *  Inport: '<Root>/Joint Angles'
   */
  adjustablePIDcontrol_3_Y.ExtraOutput[0] =
    adjustablePIDcontrol_3_U.ExternalInput[0];
  adjustablePIDcontrol_3_Y.ExtraOutput[1] =
    adjustablePIDcontrol_3_U.ExternalInput[1];
  adjustablePIDcontrol_3_Y.ExtraOutput[2] =
    adjustablePIDcontrol_3_U.ExternalInput[2];
  adjustablePIDcontrol_3_Y.ExtraOutput[3] = rtb_CommandedJointTorques_idx;
  adjustablePIDcontrol_3_Y.ExtraOutput[4] = rtb_CommandedJointTorques_idx_0;
  adjustablePIDcontrol_3_Y.ExtraOutput[5] = acc;
  adjustablePIDcontrol_3_Y.ExtraOutput[6] = adjustablePIDcontrol_3_B.Error[0];
  adjustablePIDcontrol_3_Y.ExtraOutput[7] = adjustablePIDcontrol_3_B.Error[1];
  adjustablePIDcontrol_3_Y.ExtraOutput[8] = adjustablePIDcontrol_3_B.Error[2];
  adjustablePIDcontrol_3_Y.ExtraOutput[9] =
    adjustablePIDcontrol_3_U.JointAngles[0];
  adjustablePIDcontrol_3_Y.ExtraOutput[10] =
    adjustablePIDcontrol_3_U.JointAngles[1];
  adjustablePIDcontrol_3_Y.ExtraOutput[11] =
    adjustablePIDcontrol_3_U.JointAngles[2];
  memcpy(&adjustablePIDcontrol_3_Y.ExtraOutput[12],
         &adjustablePIDcontrol_3_P.Buffer_Value[0], 20U * sizeof(real_T));
}

/* Model update function */
static void adjustablePIDcontrol_3_update(void)
{
  /* Update for DiscreteFir: '<Root>/Discrete FIR Filter' */
  adjustablePIDcontrol_3_DWork.DiscreteFIRFilter_circBuf--;
  if (adjustablePIDcontrol_3_DWork.DiscreteFIRFilter_circBuf < 0) {
    adjustablePIDcontrol_3_DWork.DiscreteFIRFilter_circBuf = 4;
  }

  adjustablePIDcontrol_3_DWork.DiscreteFIRFilter_states[adjustablePIDcontrol_3_DWork.DiscreteFIRFilter_circBuf]
    = adjustablePIDcontrol_3_B.Error[0];
  adjustablePIDcontrol_3_DWork.DiscreteFIRFilter_states[adjustablePIDcontrol_3_DWork.DiscreteFIRFilter_circBuf
    + 5] = adjustablePIDcontrol_3_B.Error[1];
  adjustablePIDcontrol_3_DWork.DiscreteFIRFilter_states[adjustablePIDcontrol_3_DWork.DiscreteFIRFilter_circBuf
    + 10] = adjustablePIDcontrol_3_B.Error[2];

  /* End of Update for DiscreteFir: '<Root>/Discrete FIR Filter' */

  /* Update for UnitDelay: '<S1>/UD' */
  adjustablePIDcontrol_3_DWork.UD_DSTATE[0] = adjustablePIDcontrol_3_B.TSamp[0];
  adjustablePIDcontrol_3_DWork.UD_DSTATE[1] = adjustablePIDcontrol_3_B.TSamp[1];
  adjustablePIDcontrol_3_DWork.UD_DSTATE[2] = adjustablePIDcontrol_3_B.TSamp[2];

  /* Update for DiscreteIntegrator: '<Root>/Discrete-Time Integrator' */
  adjustablePIDcontrol_3_DWork.DiscreteTimeIntegrator_DSTATE[0] +=
    adjustablePIDcontrol_3_P.DiscreteTimeIntegrator_gainval *
    adjustablePIDcontrol_3_B.Error[0];
  adjustablePIDcontrol_3_DWork.DiscreteTimeIntegrator_DSTATE[1] +=
    adjustablePIDcontrol_3_P.DiscreteTimeIntegrator_gainval *
    adjustablePIDcontrol_3_B.Error[1];
  adjustablePIDcontrol_3_DWork.DiscreteTimeIntegrator_DSTATE[2] +=
    adjustablePIDcontrol_3_P.DiscreteTimeIntegrator_gainval *
    adjustablePIDcontrol_3_B.Error[2];
  if (adjustablePIDcontrol_3_DWork.DiscreteTimeIntegrator_DSTATE[0] >=
      adjustablePIDcontrol_3_P.DiscreteTimeIntegrator_UpperSat[0]) {
    adjustablePIDcontrol_3_DWork.DiscreteTimeIntegrator_DSTATE[0] =
      adjustablePIDcontrol_3_P.DiscreteTimeIntegrator_UpperSat[0];
  } else {
    if (adjustablePIDcontrol_3_DWork.DiscreteTimeIntegrator_DSTATE[0] <=
        adjustablePIDcontrol_3_P.DiscreteTimeIntegrator_LowerSat[0]) {
      adjustablePIDcontrol_3_DWork.DiscreteTimeIntegrator_DSTATE[0] =
        adjustablePIDcontrol_3_P.DiscreteTimeIntegrator_LowerSat[0];
    }
  }

  if (adjustablePIDcontrol_3_DWork.DiscreteTimeIntegrator_DSTATE[1] >=
      adjustablePIDcontrol_3_P.DiscreteTimeIntegrator_UpperSat[1]) {
    adjustablePIDcontrol_3_DWork.DiscreteTimeIntegrator_DSTATE[1] =
      adjustablePIDcontrol_3_P.DiscreteTimeIntegrator_UpperSat[1];
  } else {
    if (adjustablePIDcontrol_3_DWork.DiscreteTimeIntegrator_DSTATE[1] <=
        adjustablePIDcontrol_3_P.DiscreteTimeIntegrator_LowerSat[1]) {
      adjustablePIDcontrol_3_DWork.DiscreteTimeIntegrator_DSTATE[1] =
        adjustablePIDcontrol_3_P.DiscreteTimeIntegrator_LowerSat[1];
    }
  }

  if (adjustablePIDcontrol_3_DWork.DiscreteTimeIntegrator_DSTATE[2] >=
      adjustablePIDcontrol_3_P.DiscreteTimeIntegrator_UpperSat[2]) {
    adjustablePIDcontrol_3_DWork.DiscreteTimeIntegrator_DSTATE[2] =
      adjustablePIDcontrol_3_P.DiscreteTimeIntegrator_UpperSat[2];
  } else {
    if (adjustablePIDcontrol_3_DWork.DiscreteTimeIntegrator_DSTATE[2] <=
        adjustablePIDcontrol_3_P.DiscreteTimeIntegrator_LowerSat[2]) {
      adjustablePIDcontrol_3_DWork.DiscreteTimeIntegrator_DSTATE[2] =
        adjustablePIDcontrol_3_P.DiscreteTimeIntegrator_LowerSat[2];
    }
  }

  /* End of Update for DiscreteIntegrator: '<Root>/Discrete-Time Integrator' */

  /* Update absolute time for base rate */
  /* The "clockTick0" counts the number of times the code of this task has
   * been executed. The absolute time is the multiplication of "clockTick0"
   * and "Timing.stepSize0". Size of "clockTick0" ensures timer will not
   * overflow during the application lifespan selected.
   * Timer of this task consists of two 32 bit unsigned integers.
   * The two integers represent the low bits Timing.clockTick0 and the high bits
   * Timing.clockTickH0. When the low bit overflows to 0, the high bits increment.
   */
  if (!(++adjustablePIDcontrol_3_M->Timing.clockTick0)) {
    ++adjustablePIDcontrol_3_M->Timing.clockTickH0;
  }

  adjustablePIDcontrol_3_M->Timing.t[0] =
    adjustablePIDcontrol_3_M->Timing.clockTick0 *
    adjustablePIDcontrol_3_M->Timing.stepSize0 +
    adjustablePIDcontrol_3_M->Timing.clockTickH0 *
    adjustablePIDcontrol_3_M->Timing.stepSize0 * 4294967296.0;
}

/* Model initialize function */
void adjustablePIDcontrol_3_initialize(void)
{
  {
    int32_T i;

    /* InitializeConditions for DiscreteFir: '<Root>/Discrete FIR Filter' */
    adjustablePIDcontrol_3_DWork.DiscreteFIRFilter_circBuf = 0;
    for (i = 0; i < 15; i++) {
      adjustablePIDcontrol_3_DWork.DiscreteFIRFilter_states[i] =
        adjustablePIDcontrol_3_P.DiscreteFIRFilter_InitialStates;
    }

    /* End of InitializeConditions for DiscreteFir: '<Root>/Discrete FIR Filter' */

    /* InitializeConditions for UnitDelay: '<S1>/UD' */
    adjustablePIDcontrol_3_DWork.UD_DSTATE[0] = adjustablePIDcontrol_3_P.UD_X0;
    adjustablePIDcontrol_3_DWork.UD_DSTATE[1] = adjustablePIDcontrol_3_P.UD_X0;
    adjustablePIDcontrol_3_DWork.UD_DSTATE[2] = adjustablePIDcontrol_3_P.UD_X0;

    /* InitializeConditions for DiscreteIntegrator: '<Root>/Discrete-Time Integrator' */
    adjustablePIDcontrol_3_DWork.DiscreteTimeIntegrator_DSTATE[0] =
      adjustablePIDcontrol_3_P.DiscreteTimeIntegrator_IC[0];
    adjustablePIDcontrol_3_DWork.DiscreteTimeIntegrator_DSTATE[1] =
      adjustablePIDcontrol_3_P.DiscreteTimeIntegrator_IC[1];
    adjustablePIDcontrol_3_DWork.DiscreteTimeIntegrator_DSTATE[2] =
      adjustablePIDcontrol_3_P.DiscreteTimeIntegrator_IC[2];
  }
}

/* Model terminate function */
void adjustablePIDcontrol_3_terminate(void)
{
  /* (no terminate code required) */
}

/*========================================================================*
 * Start of Classic call interface                                        *
 *========================================================================*/
void MdlOutputs(int_T tid)
{
  adjustablePIDcontrol_3_output();

  /* tid is required for a uniform function interface.
   * Argument tid is not used in the function. */
  UNUSED_PARAMETER(tid);
}

void MdlUpdate(int_T tid)
{
  adjustablePIDcontrol_3_update();

  /* tid is required for a uniform function interface.
   * Argument tid is not used in the function. */
  UNUSED_PARAMETER(tid);
}

void MdlInitializeSizes(void)
{
}

void MdlInitializeSampleTimes(void)
{
}

void MdlInitialize(void)
{
}

void MdlStart(void)
{
  adjustablePIDcontrol_3_initialize();
}

void MdlTerminate(void)
{
  adjustablePIDcontrol_3_terminate();
}

RT_MODEL_adjustablePIDcontrol_3 *adjustablePIDcontrol_3(void)
{
  /* Registration code */

  /* initialize non-finites */
  rt_InitInfAndNaN(sizeof(real_T));

  /* initialize real-time model */
  (void) memset((void *)adjustablePIDcontrol_3_M, 0,
                sizeof(RT_MODEL_adjustablePIDcontrol_3));

  /* Initialize timing info */
  {
    int_T *mdlTsMap = adjustablePIDcontrol_3_M->Timing.sampleTimeTaskIDArray;
    mdlTsMap[0] = 0;
    adjustablePIDcontrol_3_M->Timing.sampleTimeTaskIDPtr = (&mdlTsMap[0]);
    adjustablePIDcontrol_3_M->Timing.sampleTimes =
      (&adjustablePIDcontrol_3_M->Timing.sampleTimesArray[0]);
    adjustablePIDcontrol_3_M->Timing.offsetTimes =
      (&adjustablePIDcontrol_3_M->Timing.offsetTimesArray[0]);

    /* task periods */
    adjustablePIDcontrol_3_M->Timing.sampleTimes[0] = (0.001);

    /* task offsets */
    adjustablePIDcontrol_3_M->Timing.offsetTimes[0] = (0.0);
  }

  rtmSetTPtr(adjustablePIDcontrol_3_M, &adjustablePIDcontrol_3_M->Timing.tArray
             [0]);

  {
    int_T *mdlSampleHits = adjustablePIDcontrol_3_M->Timing.sampleHitArray;
    mdlSampleHits[0] = 1;
    adjustablePIDcontrol_3_M->Timing.sampleHits = (&mdlSampleHits[0]);
  }

  rtmSetTFinal(adjustablePIDcontrol_3_M, -1);
  adjustablePIDcontrol_3_M->Timing.stepSize0 = 0.001;
  adjustablePIDcontrol_3_M->solverInfoPtr =
    (&adjustablePIDcontrol_3_M->solverInfo);
  adjustablePIDcontrol_3_M->Timing.stepSize = (0.001);
  rtsiSetFixedStepSize(&adjustablePIDcontrol_3_M->solverInfo, 0.001);
  rtsiSetSolverMode(&adjustablePIDcontrol_3_M->solverInfo,
                    SOLVER_MODE_SINGLETASKING);

  /* block I/O */
  adjustablePIDcontrol_3_M->ModelData.blockIO = ((void *)
    &adjustablePIDcontrol_3_B);
  (void) memset(((void *) &adjustablePIDcontrol_3_B), 0,
                sizeof(BlockIO_adjustablePIDcontrol_3));

  /* parameters */
  adjustablePIDcontrol_3_M->ModelData.defaultParam = ((real_T *)
    &adjustablePIDcontrol_3_P);

  /* states (dwork) */
  adjustablePIDcontrol_3_M->Work.dwork = ((void *) &adjustablePIDcontrol_3_DWork);
  (void) memset((void *)&adjustablePIDcontrol_3_DWork, 0,
                sizeof(D_Work_adjustablePIDcontrol_3));

  /* external inputs */
  adjustablePIDcontrol_3_M->ModelData.inputs = (((void*)
    &adjustablePIDcontrol_3_U));
  (void) memset((void *)&adjustablePIDcontrol_3_U, 0,
                sizeof(ExternalInputs_adjustablePIDcon));

  /* external outputs */
  adjustablePIDcontrol_3_M->ModelData.outputs = (&adjustablePIDcontrol_3_Y);
  (void) memset((void *)&adjustablePIDcontrol_3_Y, 0,
                sizeof(ExternalOutputs_adjustablePIDco));

  /* Initialize Sizes */
  adjustablePIDcontrol_3_M->Sizes.numContStates = (0);/* Number of continuous states */
  adjustablePIDcontrol_3_M->Sizes.numY = (35);/* Number of model outputs */
  adjustablePIDcontrol_3_M->Sizes.numU = (22);/* Number of model inputs */
  adjustablePIDcontrol_3_M->Sizes.sysDirFeedThru = (1);/* The model is direct feedthrough */
  adjustablePIDcontrol_3_M->Sizes.numSampTimes = (1);/* Number of sample times */
  adjustablePIDcontrol_3_M->Sizes.numBlocks = (13);/* Number of blocks */
  adjustablePIDcontrol_3_M->Sizes.numBlockIO = (2);/* Number of block outputs */
  adjustablePIDcontrol_3_M->Sizes.numBlockPrms = (39);/* Sum of parameter "widths" */
  return adjustablePIDcontrol_3_M;
}

/*========================================================================*
 * End of Classic call interface                                          *
 *========================================================================*/
