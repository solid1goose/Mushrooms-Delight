import json, os

namespace = "mushrooms-delight"
base_path = "src/main/resources/assets"
lang_path = "src/main/resources/assets/mushrooms-delight/lang/en_us.json"

items = [
    "tea_amplified_hell", "tea_time_hell"
]

# --- Обновляем lang файл ---
if os.path.exists(lang_path):
    with open(lang_path, "r", encoding="utf-8") as f:
        lang = json.load(f)
else:
    lang = {}

new_entries = {}
for item_id in items:
    key = f"item.{namespace}.{item_id}"
    if key not in lang:  # не перезаписываем уже существующие
        new_entries[key] = item_id  # название = просто id

# Добавляем новые записи в НАЧАЛО
lang = {**new_entries, **lang}

os.makedirs(os.path.dirname(lang_path), exist_ok=True)
with open(lang_path, "w", encoding="utf-8") as f:
    json.dump(lang, f, indent=2, ensure_ascii=False)

print(f"Lang файл обновлён: добавлено {len(new_entries)} записей.")

# --- Генерируем JSON файлы предметов ---
for item_id in items:
    tex_path = f"{namespace}:item/{item_id}"

    os.makedirs(f"{base_path}/{namespace}/models/item", exist_ok=True)
    os.makedirs(f"{base_path}/{namespace}/items", exist_ok=True)

    with open(f"{base_path}/{namespace}/models/item/{item_id}.json", "w") as f:
        json.dump({"parent": "minecraft:item/generated", "textures": {"layer0": tex_path, "particles": tex_path}}, f, indent=2)

    with open(f"{base_path}/{namespace}/items/{item_id}.json", "w") as f:
        json.dump({"model": {"type": "minecraft:model", "model": tex_path}}, f, indent=2)

print(f"Готово! Создано {len(items) * 2} файлов предметов.")