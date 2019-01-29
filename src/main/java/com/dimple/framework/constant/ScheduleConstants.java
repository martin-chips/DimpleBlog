package com.dimple.framework.constant;

/**
 * @className: ScheduleConstants
 * @description: 任务调度的常量
 * @auther: Owenb
 * @date: 01/29/19
 * @version: 1.0
 */
public interface ScheduleConstants {
    String TASK_CLASS_NAME = "__TASK_CLASS_NAME__";

    String TASK_PROPERTIES = "__TASK_PROPERTIES__";

    /**
     * 默认
     */
    String MISFIRE_DEFAULT = "0";

    /**
     * 立即触发执行
     */
    String MISFIRE_IGNORE_MISFIRES = "1";

    /**
     * 触发一次执行
     */
    String MISFIRE_FIRE_AND_PROCEED = "2";

    /**
     * 不触发立即执行
     */
    String MISFIRE_DO_NOTHING = "3";

    enum Status {
        /**
         * 正常
         */
        NORMAL(0),
        /**
         * 暂停
         */
        PAUSE(1);

        private Integer value;

        Status(Integer value) {
            this.value = value;
        }

        public Integer getValue() {
            return value;
        }
    }
}
