from ics import Calendar, Event
from datetime import datetime
from bs4 import BeautifulSoup
import pytz

def parse_html_to_ics(html_file, ics_file):
    """
    Парсит HTML-файл с расписанием и создает ICS-файл с событиями (дедлайнами) и напоминаниями.
    Время событий устанавливается строго по расписанию в указанной временной зоне.
    """
    # Создаем календарь
    cal = Calendar()

    # Определяем целевую временную зону (замените при необходимости)
    target_timezone = pytz.timezone('Europe/Moscow')  # <-- ЗДЕСЬ МОЖНО ИЗМЕНИТЬ ВРЕМЕННУЮ ЗОНУ

    # Читаем HTML-файл
    with open(html_file, 'r', encoding='utf-8') as f:
        html_content = f.read()

    # Парсим HTML с помощью BeautifulSoup
    soup = BeautifulSoup(html_content, 'html.parser')

    # Находим все контейнеры с расписанием
    containers = soup.find_all('div', class_='schedule-tab__container')

    for container in containers:
        # Получаем название курса/раздела
        course_title_tag = container.find('h6', class_='sf-title--6')
        course_title = course_title_tag.get_text(strip=True) if course_title_tag else "Неизвестный курс"

        # Находим все строки с информацией (кроме заголовка таблицы)
        info_rows = container.find_all('div', class_='schedule-tab__container-info')[1:]  # Пропускаем первую строку (заголовки)

        for row in info_rows:
            spans = row.find_all('span', class_='sf-text')
            if len(spans) < 3:
                continue  # Пропускаем строки, не содержащие нужные данные

            item_title = spans[0].get_text(strip=True)
            release_date_str = spans[1].get_text(strip=True)
            deadline_str = spans[2].get_text(strip=True)

            # Обрабатываем только элементы с указанным дедлайном (не "Не указан")
            if deadline_str != "Не указан":
                try:
                    # Парсим дату и время дедлайна
                    # Формат: "14.09.2025, 23:59:00"
                    naive_deadline_dt = datetime.strptime(deadline_str, "%d.%m.%Y, %H:%M:%S")
                    
                    # Привязываем это "наивное" время к указанной временной зоне
                    localized_deadline_dt = target_timezone.localize(naive_deadline_dt)

                    # Создаем событие
                    event = Event()
                    event.name = f"[Дедлайн] {item_title}"
                    event.description = f"Курс: {course_title}\nДата выхода: {release_date_str}"
                    
                    # Устанавливаем дату и время события (дедлайна) с учетом временной зоны
                    event.begin = localized_deadline_dt
                    event.end = localized_deadline_dt  # Можно установить продолжительность, если нужно

                    # Добавляем напоминания
                    # Напоминание за неделю (7 дней * 24 часа * 60 минут = 10080 минут)
                    event.alarms.append({"action": "DISPLAY", "trigger": "-PT10080M"})
                    # Напоминание за 3 дня (3 * 24 * 60 = 4320 минут)
                    event.alarms.append({"action": "DISPLAY", "trigger": "-PT4320M"})
                    # Напоминание за 1 день (1440 минут)
                    event.alarms.append({"action": "DISPLAY", "trigger": "-PT1440M"})

                    # Добавляем событие в календарь
                    cal.events.add(event)

                except ValueError as e:
                    print(f"Ошибка при парсинге даты для '{item_title}': {e}")
                    continue

    # Записываем календарь в ICS-файл
    with open(ics_file, 'w', encoding='utf-8') as f:
        f.writelines(cal.serialize_iter())

    print(f"ICS-файл '{ics_file}' успешно создан с {len(cal.events)} событиями.")

# Запуск скрипта
if __name__ == "__main__":
    parse_html_to_ics('schedule.txt', 'schedule.ics')