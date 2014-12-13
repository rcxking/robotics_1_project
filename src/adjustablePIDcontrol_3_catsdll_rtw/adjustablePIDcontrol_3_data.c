/*
 * adjustablePIDcontrol_3_data.c
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

/* Block parameters (auto storage) */
Parameters_adjustablePIDcontrol adjustablePIDcontrol_3_P = {
  0.0,                                 /* Expression: 0
                                        * Referenced by: '<Root>/Discrete FIR Filter'
                                        */

  /*  Expression: [ 1 1 1 1 1 1 ] / 6
   * Referenced by: '<Root>/Discrete FIR Filter'
   */
  { 0.16666666666666666, 0.16666666666666666, 0.16666666666666666,
    0.16666666666666666, 0.16666666666666666, 0.16666666666666666 },
  1000.0,                              /* Computed Parameter: TSamp_WtEt
                                        * Referenced by: '<S1>/TSamp'
                                        */
  0.0,                                 /* Expression: ICPrevScaledInput
                                        * Referenced by: '<S1>/UD'
                                        */
  0.001,                               /* Computed Parameter: DiscreteTimeIntegrator_gainval
                                        * Referenced by: '<Root>/Discrete-Time Integrator'
                                        */

  /*  Expression: [0 0.11 0]
   * Referenced by: '<Root>/Discrete-Time Integrator'
   */
  { 0.0, 0.11, 0.0 },

  /*  Expression: [2 1.87 1.07]
   * Referenced by: '<Root>/Discrete-Time Integrator'
   */
  { 2.0, 1.87, 1.07 },

  /*  Expression: [-2 0.11 -0.98]
   * Referenced by: '<Root>/Discrete-Time Integrator'
   */
  { -2.0, 0.11, -0.98 },

  /*  Expression: zeros(20,1)
   * Referenced by: '<Root>/Buffer'
   */
  { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
    0.0, 0.0, 0.0, 0.0, 0.0 }
};
