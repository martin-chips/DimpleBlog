/**
 * @desc 获取随机字符串
 * @param {*} digits: Number   指定位数
 * @param {*} join:   String   分隔字符串
 * @returns String
 * @auther justJokee
 */
export function getRandomCharacter(digits = 4, join = '') {
  const words = [
    'A',
    'B',
    'C',
    'D',
    'E',
    'F',
    'G',
    'H',
    'I',
    'J',
    'K',
    'L',
    'M',
    'N',
    'O',
    'P',
    'Q',
    'R',
    'S',
    'T',
    'U',
    'V',
    'W',
    'X',
    'Y',
    'Z'
  ]
  if (typeof digits !== 'number') throw new Error('参数必须为 number 类型')
  return Array(digits)
    .fill()
    .map(() => words[Math.ceil(Math.random() * 1000) % 26])
    .join(join)
}
